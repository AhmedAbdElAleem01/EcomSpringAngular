package com.springboot.bakefinity.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class AddressDTO {
    private Integer id;
    private Integer userId;
    private Integer buildingNo;
    private String street;
    private String city;
    private String country;
}