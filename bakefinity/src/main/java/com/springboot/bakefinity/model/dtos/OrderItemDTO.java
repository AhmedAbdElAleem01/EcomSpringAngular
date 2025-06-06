package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO implements Serializable {
    private Integer userId;
    private String userName;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Double price;
    private int quantity;

    public OrderItemDTO(Integer userId, Integer productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

}