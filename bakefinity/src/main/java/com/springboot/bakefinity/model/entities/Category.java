package com.springboot.bakefinity.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@ToString(exclude = {"users", "products"})
@Builder
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @NonNull
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @NonNull
    @Column(name = "description", length = 255)
    private String description;

    @NonNull
    @Column(name = "imageUrl", nullable = false, length = 255)
    private String imageUrl;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    private Set<User> users = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private Set<Product> products = new HashSet<>();

}
