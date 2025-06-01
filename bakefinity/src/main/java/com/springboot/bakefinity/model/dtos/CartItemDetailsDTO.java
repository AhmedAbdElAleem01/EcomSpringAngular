package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class CartItemDetailsDTO implements Serializable {
    private Integer quantity;
    private String name;
    private String description;
    private Double totalPricePerProduct;
}