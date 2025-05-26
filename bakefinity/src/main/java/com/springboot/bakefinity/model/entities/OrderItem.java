package com.bakefinity.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "orderitem")
public class OrderItem implements Serializable {
    private OrderItemId id;
    private Product product;
    private Order order;
    private Integer quantity;

    public OrderItem() {
    }

    public OrderItem(OrderItemId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "productId", column = @Column(name = "productId", nullable = false)),
            @AttributeOverride(name = "orderId", column = @Column(name = "orderId", nullable = false))
    })
    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", nullable = false, insertable = false, updatable = false)
    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", nullable = false, insertable = false, updatable = false)
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}