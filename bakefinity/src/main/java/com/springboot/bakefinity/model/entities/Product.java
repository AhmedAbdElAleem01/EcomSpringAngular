package com.springboot.bakefinity.model.entities;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "product")
@SQLDelete(sql = "UPDATE product SET deleted = true WHERE id=?")  //override the delete command
public class Product implements Serializable {
    private Integer id;
    private Category category;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Integer stockQuantity;
    private String ingredients;
    private boolean deleted = Boolean.FALSE;
    private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
    private Set<CartItem> cartItems = new HashSet<CartItem>(0);

    public Product() {
    }

    public Product(Category category, String name, Double price, String imageUrl, Integer stockQuantity) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
    }

    public Product(Integer id, Category category, String name, String description, Double price, String imageUrl, Integer stockQuantity, String ingredients, Set<OrderItem> orderItems, Set<CartItem> cartItems) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.ingredients = ingredients;
        this.orderItems = orderItems;
        this.cartItems = cartItems;
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
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public boolean isDeleted() {
        return deleted;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", nullable = false)
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", length = 255)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price", nullable = false, precision = 10)
    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "imageUrl", nullable = false, length = 255)
    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "stockQuantity", nullable = false)
    public Integer getStockQuantity() {
        return this.stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Column(name = "ingredients", length = 255)
    public String getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}