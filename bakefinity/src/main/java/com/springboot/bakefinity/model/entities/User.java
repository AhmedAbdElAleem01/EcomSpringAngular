package com.springboot.bakefinity.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user",  uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")
})
public class User implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Double creditLimit;
    private Date birthDate;
    private String job;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Set<Order> orders = new HashSet<Order>(0);
    private Set<CartItem> cartItems = new HashSet<CartItem>(0);
    private Set<Category> categories = new HashSet<Category>(0);
    private Set<Address> addresses = new HashSet<Address>(0);

    public User() {
    }

    public User(String username, String name, String email, String password, String phoneNumber, Double creditLimit, String job, Date birthDate) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
        this.job = job;
        this.birthDate = birthDate;
    }

    public User(Integer id, String username, String name, String email, String password, String phoneNumber, Double creditLimit, Date birthDate, String job, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
        this.job = job;
        this.createdAt = createdAt;
    }

    public User(Integer id, String name, String username, String email, String password, String phoneNumber, Double creditLimit, Date birthDate, String job, LocalDateTime createdAt, Set<Order> orders, Set<CartItem> cartItems, Set<Category> categories, Set<Address> addresses) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.creditLimit = creditLimit;
        this.birthDate = birthDate;
        this.job = job;
        this.createdAt = createdAt;
        this.orders = orders;
        this.cartItems = cartItems;
        this.categories = categories;
        this.addresses = addresses;
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

    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "username", unique = true, nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "email", unique = true, nullable = false, length = 255)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "phoneNumber", nullable = false, length = 20)
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "creditLimit", nullable = false, precision = 15)
    public Double getCreditLimit() {
        return this.creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @Temporal(TemporalType.DATE) // store only date (ignore time)
    @Column(name = "birthDate")
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "job", length = 255)
    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Column(name = "createdAt", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "userinterests", joinColumns = {
            @JoinColumn(name = "userId", nullable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "categoryId", nullable = false) })
    public Set<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

}