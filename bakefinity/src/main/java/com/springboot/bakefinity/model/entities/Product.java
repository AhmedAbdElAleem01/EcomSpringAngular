package com.springboot.bakefinity.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ToString(exclude = {"orderItems", "cartItems"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NonNull
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;

    @NonNull
    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String description;

    @NonNull
    @Column(nullable = false, precision = 10)
    private Double price;

    @NonNull
    @Column(nullable = false, length = 255)
    private String imageUrl;

    @NonNull
    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(length = 255)
    private String ingredients;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<OrderItem> orderItems = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<CartItem> cartItems = new HashSet<>();
}
