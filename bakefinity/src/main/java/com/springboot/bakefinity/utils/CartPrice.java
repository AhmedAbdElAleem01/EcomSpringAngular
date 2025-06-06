package com.springboot.bakefinity.utils;

import com.springboot.bakefinity.model.dtos.CartItemDetailsDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartPrice {
    public double calculateTotalPrice(List<CartItemDetailsDTO> cartItems){
        double totalPrice = 0;
        for (CartItemDetailsDTO cartItem : cartItems) {
            totalPrice += cartItem.getTotalPricePerProduct();
        }
        return Math.round(totalPrice * 100.0) / 100.0;
    }
}