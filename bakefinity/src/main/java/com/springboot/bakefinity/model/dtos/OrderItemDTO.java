package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class OrderItemDTO {
    private Integer userId;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Double price;
    private int quantity;
}