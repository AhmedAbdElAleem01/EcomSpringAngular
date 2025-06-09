package com.springboot.bakefinity.controllers;

import com.springboot.bakefinity.model.dtos.*;
import com.springboot.bakefinity.model.entities.*;
import com.springboot.bakefinity.model.enums.OrderStatus;
import com.springboot.bakefinity.model.enums.PaymentMethod;
import com.springboot.bakefinity.services.impls.CartService;
import com.springboot.bakefinity.services.interfaces.*;
import com.springboot.bakefinity.utils.CartPrice;
import com.springboot.bakefinity.utils.EmailUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private CartService cartService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CartPrice cartPrice;


    @PostMapping("user/{userId}")
    public ResponseEntity<String> checkout(@PathVariable Integer userId, @RequestBody @Valid BillingDetailsDTO body) throws SQLException {
        List<CartItemDetailsDTO> cartItems = cartService.getCartItems(userId);
        // check stock quantity then create order
        for(CartItemDetailsDTO cartItem : cartItems) {
            int productId = cartItem.getProductId();
            if(productService.getProductById(productId).getStockQuantity() < cartItem.getQuantity()){
                return ResponseEntity.badRequest().body("Error: Sorry, the requested quantity exceeds the available stock for some products. Please adjust your order accordingly.");
                // return "redirect:/cart";
            }
        }
        double totalCost = cartPrice.calculateTotalPrice(cartItems);
        UserDTO user = orderService.getUserById(userId);
        if(totalCost > user.getCreditLimit()){
            return ResponseEntity.badRequest().body("Error: The total cost of your cart exceeds your credit limit. Please remove some items or increase your credit to proceed.");
        }
        OrderDTO order = new OrderDTO(userId, totalCost, PaymentMethod.CREDIT_CARD, LocalDateTime.now(), OrderStatus.SHIPPED);
        int newOrderId = orderService.create(order);
        if(newOrderId <= 0) return ResponseEntity.internalServerError().body("Error in creating order!!!");
        System.out.println("step1 is done....");

        // update user's credit limit
        UserDTO updatedUser = profileService.updateCreditLimit(userId, user.getCreditLimit() - totalCost);
        if(updatedUser == null){
            orderService.updateStatus(newOrderId, OrderStatus.FAILED);
            return ResponseEntity.internalServerError().body("Error in updating user credit limit!!!");
        }
        System.out.println("updated user: " + updatedUser);
        System.out.println("step2 is done....");

        // update stock quantity
        for(CartItemDetailsDTO cartItem : cartItems) {
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
        for(CartItemDetailsDTO cartItem : cartItems) {
            OrderItem orderItem = orderItemService.create(new OrderItem(new OrderItemId(cartItem.getProductId(), newOrderId), cartItem.getQuantity()));
            if(orderItem == null){
                orderService.updateStatus(newOrderId, OrderStatus.FAILED);
                return ResponseEntity.internalServerError().body("Error in creating orderItem!!!");
            }
        }
        System.out.println("step4 is done....");

        // send email
        StringBuilder orderDetails = new StringBuilder();
        for(CartItemDetailsDTO cartItem : cartItems) {
            orderDetails.append("Product Name: " + cartItem.getName() + "\nPrice: EGP " + cartItem.getUnit_price() + "\nQuantity: " + cartItem.getQuantity() + "\n");
        }
        orderDetails.append("Total Cost: EGP " + totalCost);
        EmailUtil.sendOrderConfirmationEmail(user.getEmail(), user.getName(), orderDetails.toString());
        System.out.println("step5 is done....");

        // clear cart
        cartService.clearCart(userId);

        return ResponseEntity.ok("Order is placed successfully");  // return "redirect:/confirmation";
    }


    @GetMapping("/user/{userId}/summary")
    public ResponseEntity<Map<String, Object>> getOrderSummary(@PathVariable Integer userId) {
        List<CartItemDetailsDTO> cartItems = cartService.getCartItems(userId);
        double totalCost = cartPrice.calculateTotalPrice(cartItems);
        UserDTO user = orderService.getUserById(userId);
        Map<String, Object> response = new HashMap<>();
        response.put("cartItems", cartItems);
        response.put("totalCost", totalCost);
        response.put("user", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}/info")
    public ResponseEntity<Map<String, BillingDetailsDTO>> getBillingDetails(@PathVariable Integer userId) {
        UserDTO user = orderService.getUserById(userId);
        Optional<AddressDTO> address = addressService.getAddressByUserId(userId);
        Map<String, BillingDetailsDTO> response = new HashMap<>();
        response.put("billingDetails", new BillingDetailsDTO(user.getName(), user.getEmail(), user.getPhoneNumber(), address.get().getCountry(), address.get().getCity(), address.get().getStreet(), address.get().getBuildingNo()));
        return ResponseEntity.ok(response);
    }
}