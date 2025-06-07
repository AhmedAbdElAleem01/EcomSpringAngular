package com.springboot.bakefinity.model.dtos;


import com.springboot.bakefinity.model.entities.Authority;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class UserRegistrationRequestDTO {

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @Pattern(regexp = "\\d{10}")
    private String phoneNumber;

    @NotBlank
    private String birthdate; // Format MM-dd-yyyy

    private String job;

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

    private List<Authority> authorities;

    private String name;

    @NotBlank
    private String creditLimit;

    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    private String buildingNo;

    private List<String> interests;
}
