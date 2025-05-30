package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DeliveryDetails {
    private String name;
    private String email;
    private String phone;
    private String country;
    private String city;
    private String street;
    private Integer buildingNumber;
}