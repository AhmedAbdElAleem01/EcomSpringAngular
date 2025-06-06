package com.springboot.bakefinity.model.dtos;

import com.springboot.bakefinity.model.enums.OrderStatus;
import com.springboot.bakefinity.model.enums.PaymentMethod;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class OrderDTO implements Serializable {
    private Integer id;
    private Integer userId;
    private double totalCost;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderedAt;
    private OrderStatus status;

    public OrderDTO(Integer userId, double totalCost, PaymentMethod paymentMethod, LocalDateTime orderedAt, OrderStatus status) {
        this.userId = userId;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.orderedAt = orderedAt;
        this.status = status;
    }
}