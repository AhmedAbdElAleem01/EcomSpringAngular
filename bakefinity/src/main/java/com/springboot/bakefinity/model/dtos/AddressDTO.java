package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class AddressDTO implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer buildingNo;
    private String street;
    private String city;
    private String country;
}