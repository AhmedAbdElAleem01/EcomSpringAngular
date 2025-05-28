package com.springboot.bakefinity.model.dtos;

import com.springboot.bakefinity.model.enums.OrderStatus;
import com.springboot.bakefinity.model.enums.PaymentMethod;

import java.time.LocalDateTime;

public class OrderDTO {
    private int id;
    private int userId;
    private Double totalCost;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderedAt;
    private OrderStatus status;

    public OrderDTO() {}

    public OrderDTO(int userId, double totalCost, PaymentMethod paymentMethod, LocalDateTime orderedAt, OrderStatus status) {
        this.userId = userId;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.orderedAt = orderedAt;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Double getTotalCost() { return totalCost; }
    public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }
    public LocalDateTime getOrderedAt() { return orderedAt; }
    public void setOrderedAt(LocalDateTime orderedAt) { this.orderedAt = orderedAt; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
