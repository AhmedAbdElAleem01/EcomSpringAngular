package com.springboot.bakefinity.model.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.springboot.bakefinity.model.enums.OrderStatus;
import com.springboot.bakefinity.model.enums.PaymentMethod;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private Integer id;
    private User user;
    private double totalCost;
    private PaymentMethod paymentMethod;
    private LocalDateTime orderedAt = LocalDateTime.now();
    private OrderStatus status;
    private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);

    public Order() {
    }

    public Order(User user, double totalCost, PaymentMethod paymentMethod, LocalDateTime orderedAt, OrderStatus status) {
        this.user = user;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.orderedAt = orderedAt;
        this.status = status;
    }

    public Order(User user, double totalCost, PaymentMethod paymentMethod, OrderStatus status) {
        this.user = user;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Order(User user, double totalCost, PaymentMethod paymentMethod, LocalDateTime orderedAt, OrderStatus status, Set<OrderItem> orderItems) {
        this.user = user;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
        this.orderedAt = orderedAt;
        this.status = status;
        this.orderItems = orderItems;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "totalCost", nullable = false, precision = 15)
    public double getTotalCost() {
        return this.totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod", nullable = false, length = 11)
    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Column(name = "orderedAt", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime getOrderedAt() {
        return this.orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 9)
    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", totalCost=" + totalCost +
                ", paymentMethod=" + paymentMethod +
                ", orderedAt=" + orderedAt +
                ", status=" + status +
                ", orderItems=" + orderItems +
                '}';
    }
}