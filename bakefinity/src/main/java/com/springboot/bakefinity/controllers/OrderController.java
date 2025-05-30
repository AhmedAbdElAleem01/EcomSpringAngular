package com.springboot.bakefinity.controllers;

import com.springboot.bakefinity.model.dtos.*;
import com.springboot.bakefinity.model.entities.*;
import com.springboot.bakefinity.model.enums.OrderStatus;
import com.springboot.bakefinity.model.enums.PaymentMethod;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.*;
import com.springboot.bakefinity.utils.CartPrice;
import com.springboot.bakefinity.utils.EmailUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController { // checkout
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CartPrice cartPrice;


    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@SessionAttribute(name = "user") UserDTO user, @SessionAttribute(name = "cart") Map<Integer, CartDTO> cart, HttpSession session) throws SQLException {
        // check stock quantity then create order
        for(CartDTO cartItem : cart.values()){
            int productId = cartItem.getProductId();
            if(productService.getProductById(productId).getStockQuantity() < cartItem.getQuantity()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: Sorry, the requested quantity exceeds the available stock for some products. Please adjust your order accordingly.");
                // return "redirect:/cart";
            }
        }
        double totalCost = cartPrice.calculateTotalPrice(cart);
        OrderDTO order = new OrderDTO(user.getId(), totalCost, PaymentMethod.CREDIT_CARD, LocalDateTime.now(), OrderStatus.SHIPPED);
        int newOrderId = orderService.create(order);
        if(newOrderId <= 0) return ResponseEntity.internalServerError().body("Error in creating order!!!");
        System.out.println("step1 is done....");

        // update user's credit limit
        int affectedRows = profileService.updateCreditLimit(user, user.getCreditLimit() - totalCost);
        if(affectedRows > 0) {
            session.setAttribute("user", user); // updated user
        }
        else{
            orderService.updateStatus(newOrderId, OrderStatus.FAILED);
            return ResponseEntity.internalServerError().body("Error in updating user credit limit!!!");
        }
        System.out.println("step2 is done....");

        // update stock quantity
        for(CartDTO cartItem : cart.values()){
            int productId = cartItem.getProductId();
            int newStockQuantity = productService.getProductById(productId).getStockQuantity() - cartItem.getQuantity();
            boolean updated = productService.updateStockQuantity(productId, newStockQuantity);
            if (!updated) {
                orderService.updateStatus(newOrderId, OrderStatus.FAILED);
                return ResponseEntity.internalServerError().body("Error in updating stock quantity!!!");
            }
        }
        System.out.println("step3 is done....");

        // create row in orderItems table for each cart item
        for(CartDTO cartItem : cart.values()){
            OrderItem orderItem = orderItemService.create(new OrderItem(new OrderItemId(cartItem.getProductId(), newOrderId), cartItem.getQuantity()));
            if(orderItem == null){
                orderService.updateStatus(newOrderId, OrderStatus.FAILED);
                return ResponseEntity.internalServerError().body("Error in creating orderItem!!!");
            }
        }
        System.out.println("step4 is done....");

        // send email
        StringBuilder orderDetails = new StringBuilder();
        for(CartDTO cartItem : cart.values()){
            int productId = cartItem.getProductId();
            ProductDTO curProduct = productService.getProductById(productId);
            orderDetails.append("Product Name: " + curProduct.getName() + "\nPrice: EGP " + curProduct.getPrice() + "\nQuantity: " + cartItem.getQuantity() + "\n");
        }
        orderDetails.append("Total Cost: EGP " + totalCost);
        EmailUtil.sendOrderConfirmationEmail(user.getEmail(), user.getName(), orderDetails.toString());
        System.out.println("step5 is done....");

        // clear cart
        cart.clear();

        return ResponseEntity.ok("Order is placed successfully");  // return "redirect:/confirmation";
    }


    // right side
    @GetMapping("/cart/details")
    public ResponseEntity<List<CartItemDetails>> getUserCartItems(@SessionAttribute("cart") Map<Integer, CartDTO> cart){
        List<CartItemDetails> cartItemDetailsList = new ArrayList<>();
        for(CartDTO cartItem : cart.values()){
            ProductDTO product = productService.getProductById(cartItem.getProductId());
            Double totalPerProduct = cartItem.getQuantity() * product.getPrice();
            CartItemDetails cartItemDetails = new CartItemDetails(cartItem.getQuantity(), product.getName(), product.getDescription(), totalPerProduct);
            cartItemDetailsList.add(cartItemDetails);
        }
        return ResponseEntity.ok(cartItemDetailsList);
    }

    @GetMapping("/cost")
    public ResponseEntity<Double> getTotalCost(@SessionAttribute("cart") Map<Integer, CartDTO> cart){
        return ResponseEntity.ok(cartPrice.calculateTotalPrice(cart));
    }


    // left side
    @GetMapping("/delivery/info")
    public ResponseEntity<DeliveryDetails> getUserInfo(@SessionAttribute("user") UserDTO user){
        Optional<AddressDTO> address = addressService.getAddressByUserId(user.getId());
        DeliveryDetails details = new DeliveryDetails(user.getName(), user.getEmail(), user.getPhoneNumber(), address.get().getCountry(), address.get().getCity(), address.get().getStreet(), address.get().getBuildingNo());
        return ResponseEntity.ok(details);
    }


    /* just for testing */
    @PostMapping("/test/session")
    public ResponseEntity<String> simulateSession(HttpSession session) {
        // put a fake user on session
        System.out.println("current user => " + orderService.getCurrentUser(58));
        session.setAttribute("user", orderService.getCurrentUser(58)); // any user from DB

        // put a fake cart on session
        Map<Integer, CartDTO> cart = new HashMap<>();
        cart.put(5, new CartDTO(new CartItemId(5, 58), 2));
        session.setAttribute("cart", cart);

        return ResponseEntity.ok("session is created");
    }
}