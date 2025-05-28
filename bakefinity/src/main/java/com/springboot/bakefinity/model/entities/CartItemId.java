package com.springboot.bakefinity.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class CartItemId implements java.io.Serializable {
    private Integer productId;
    private Integer userId;

    public CartItemId() {
    }

    public CartItemId(Integer productId, Integer userId) {
        this.productId = productId;
        this.userId = userId;
    }

    @Column(name = "productId", nullable = false)
    public Integer getProductId() {
        return this.productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Column(name = "userId")
    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}