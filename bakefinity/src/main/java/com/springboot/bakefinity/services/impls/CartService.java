package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.exceptions.ResourceNotFoundException;
import com.springboot.bakefinity.mappers.CartMapper;
import com.springboot.bakefinity.mappers.UserMapper;
import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.dtos.CartItemDetailsDTO;
import com.springboot.bakefinity.model.entities.CartItem;
import com.springboot.bakefinity.model.entities.CartItemId;
import com.springboot.bakefinity.model.entities.Product;
import com.springboot.bakefinity.model.entities.User;
import com.springboot.bakefinity.repositories.interfaces.CartRepo;
import com.springboot.bakefinity.repositories.interfaces.ProductRepo;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    private CartRepo cartRepo;
    public List<CartItemDetailsDTO> getCartItems(Integer userId) {

            List<CartItem> cartItems = cartRepo.findByIdUserId(userId);
            return cartMapper.toCartItemDetailsDTOList(cartItems);

    }

    public CartItemDetailsDTO addCartItem(CartDTO cartItem) {

        Product p= productRepo.findById(cartItem.getProductId()).orElseThrow(()-> new  ResourceNotFoundException("Product Not Found"));
        int availableQuantity=p.getStockQuantity();
        CartItem entity=null;
            if(cartItem.getQuantity()==0)
            {
                cartRepo.deleteById(new CartItemId(cartItem.getUserId(), cartItem.getProductId()));
            }
            else if(cartRepo.findByUserIdAndProductId(cartItem.getUserId(), cartItem.getProductId()).isPresent())
            {
                entity=cartRepo.findByUserIdAndProductId(cartItem.getUserId(), cartItem.getProductId()).get();
                entity.setQuantity(Math.min(entity.getQuantity()+cartItem.getQuantity(),availableQuantity));

            }
            else
            {

                entity = cartMapper.toEntity(cartItem);
                User u=userRepo.findById(cartItem.getUserId()).orElseThrow(()-> new ResourceNotFoundException("User Not Found"));
                entity.setUser(u);
                entity.setProduct(p);
                entity.setQuantity(Math.min(cartItem.getQuantity(),availableQuantity));
                cartRepo.save(entity);
            }
        return cartMapper.toCartItemDetailsDTO(entity);

    }
    public CartItemDetailsDTO updateCartItem(CartDTO cartItem) {

            CartItem entity=cartRepo.findByUserIdAndProductId(cartItem.getUserId(), cartItem.getProductId()).orElseThrow(()-> new ResourceNotFoundException("Cart Item Not Found"));
            entity.setQuantity(Math.min(cartItem.getQuantity(),entity.getProduct().getStockQuantity()));
        return cartMapper.toCartItemDetailsDTO(entity);

    }
    public void removeCartItem(Integer userId, Integer productId) {
        try {
            cartRepo.deleteByUserIdAndProductId(userId, productId);

        } catch (Exception e) {
            throw new RuntimeException("Failed to remove cart item", e);
        }
    }
    public void clearCart(Integer userId) {
        try {
            cartRepo.deleteByIdUserId(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear cart", e);
        }
    }

}
