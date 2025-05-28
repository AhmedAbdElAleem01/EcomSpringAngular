package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;

import java.util.Optional;

public interface ProfileRepo {    
    public Optional<UserDTO> updateCreditLimit(UserDTO user , double creditLimit);
    public Optional<AddressDTO> updateShippingAddress(UserDTO user, String country, String city, String street, String bNo);
    public Optional<UserDTO> updateAccountDetails(UserDTO user, String username, String job, String email);
    public Optional<UserDTO> updatePassword(UserDTO user, String newPass);
    public boolean isUsernameTaken(String username);
    public Optional<UserDTO> updatePhoneNumber(UserDTO user, String mobile);
}