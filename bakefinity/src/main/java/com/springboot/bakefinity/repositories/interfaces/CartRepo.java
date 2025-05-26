package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.entities.CartItem;
import java.util.List;

public interface CartRepo{
    void add(CartItem cartItem) throws Exception;
    void update(CartItem cartItem) throws Exception;
    List<CartItem> getAllByUserId(int userId) throws Exception;
    CartItem get(int userId, int productId) throws Exception;
    void delete(int userId, int productId) throws Exception;
    void clearUserCart(int userId) throws Exception;
}
