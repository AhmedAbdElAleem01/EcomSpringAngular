package com.springboot.bakefinity.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class BillingDetailsDTO implements Serializable {
    private String name;

    private String email;

    @NotBlank(message = "Phone Number is required")
    @Pattern(regexp = "^01[0-2,5]\\d{8}$", message = "Invalid Phone Number")
    private String phone;

    private String country;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^[A-Za-z]+([\\s-][A-Za-z]+)*$", message = "Invalid City Name")
    private String city;

    @NotBlank(message = "Street is required")
    @Pattern(regexp = "^[A-Za-z]+([\\s-][A-Za-z]+)*$", message = "Invalid Street Name")
    private String street;

    @NotNull(message = "Building Number is required")
    private Integer buildingNumber;
}