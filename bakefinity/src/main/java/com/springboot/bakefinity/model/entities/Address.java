package com.springboot.bakefinity.model.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {
    private Integer id;
    private User user;
    private Integer buildingNo;
    private String street;
    private String city;
    private String country;

    public Address() {
    }

    public Address(Integer id, User user, Integer buildingNo, String street, String city, String country) {
        this.id = id;
        this.user = user;
        this.buildingNo = buildingNo;
        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Address(User user, Integer buildingNo, String street, String city, String country) {
        this.user = user;
        this.buildingNo = buildingNo;
        this.street = street;
        this.city = city;
        this.country = country;
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

    @Column(name = "buildingNo", length = 255)
    public Integer getBuildingNo() {
        return this.buildingNo;
    }

    public void setBuildingNo(Integer buildingNo) {
        this.buildingNo = buildingNo;
    }

    @Column(name = "street", length = 255)
    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "city", length = 255)
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "country", length = 255)
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}