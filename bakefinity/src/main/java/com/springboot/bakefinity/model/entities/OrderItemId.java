package com.springboot.bakefinity.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {
    private Integer orderId;
    private Integer productId;

    public OrderItemId() {
    }

    public OrderItemId(Integer productId, Integer orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    @Column(name = "productId", nullable = false)
    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "orderId", nullable = false)
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}

