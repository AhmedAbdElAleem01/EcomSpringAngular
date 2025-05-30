package com.springboot.bakefinity.services;

import com.springboot.bakefinity.mappers.CartMapper;
import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.entities.CartItem;
import com.springboot.bakefinity.model.entities.CartItemId;
import com.springboot.bakefinity.repositories.interfaces.CartRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {
    @Autowired
    CartMapper cartMapper;
    @Autowired
    private CartRepo cartRepo;
    public List<CartDTO> getCartItems(Integer userId) {
        try {
            List<CartItem> cartItems = cartRepo.findByIdUserId(userId);
            return cartMapper.toDtoList(cartItems);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve cart items", e);
        }
    }
    public void addCartItem(CartDTO cartItem) {
        try {
            CartItem entity = cartMapper.toEntity(cartItem);
            cartRepo.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add cart item", e);
        }
    }
    public void updateCartItem(CartDTO cartItem) {
        try {
            CartItem entity = new CartItem();
            entity.setId(new CartItemId(cartItem.getProductId(), cartItem.getUserId()));
            entity.setQuantity(cartItem.getQuantity());
            //cartRepo.update(entity);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update cart item", e);
        }
    }
    public void removeCartItem(Integer userId, Integer productId) {
        try {
           // cartRepo.delete(userId, productId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove cart item", e);
        }
    }
    public void clearCart(Integer userId) {
        try {
            //cartRepo.clearUserCart(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to clear cart", e);
        }
    }

}
