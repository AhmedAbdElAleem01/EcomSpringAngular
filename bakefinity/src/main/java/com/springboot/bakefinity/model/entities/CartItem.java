package com.springboot.bakefinity.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cartitem")
public class CartItem implements Serializable {
    private CartItemId id;
    private Product product;
    private User user;
    private Integer quantity;

    public CartItem() {
    }

    public CartItem(CartItemId id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public CartItem(CartItemId id, Product product, User user, Integer quantity) {
        this.id = id;
        this.product = product;
        this.user = user;
        this.quantity = quantity;
    }

    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "productId", column = @Column(name = "productId", nullable = false)),
            @AttributeOverride(name = "userId", column = @Column(name = "userId"))
    })
    public CartItemId getId() {
        return this.id;
    }

    public void setId(CartItemId id) {
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
    @JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(id, cartItem.id)   && Objects.equals(quantity, cartItem.quantity);
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", user=" + user +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, user, quantity);
    }
}