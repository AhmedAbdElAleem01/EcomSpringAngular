package com.springboot.bakefinity.utils;

import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.dtos.ProductDTO;
import com.springboot.bakefinity.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class CartPrice {
    @Autowired
    private ProductService productService;

    public double calculateTotalPrice(Map<Integer, CartDTO> cart){
        double totalPrice = 0;
        for (CartDTO cartItem : cart.values()) {
            ProductDTO product = productService.getProductById(cartItem.getProductId());
            if (product != null) {
                totalPrice += product.getPrice() * cartItem.getQuantity();
            }
        }
        return Math.round(totalPrice * 100.0) / 100.0;
    }
}