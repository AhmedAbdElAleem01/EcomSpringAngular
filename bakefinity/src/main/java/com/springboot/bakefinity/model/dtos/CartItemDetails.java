package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemDetails {
    private Integer quantity;
    private String name;
    private String description;
    private Double totalPricePerProduct;
}
