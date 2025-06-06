package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDetailsDTO implements Serializable {

    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private String name;
    private String description;
    private Double totalPricePerProduct;
    private String image_url;
    private Integer available_quantity;
    private Double unit_price;

    public CartItemDetailsDTO(Integer quantity, String name, String description, Double totalPricePerProduct) {
        this.quantity = quantity;
        this.name = name;
        this.description = description;
        this.totalPricePerProduct = totalPricePerProduct;
    }
}