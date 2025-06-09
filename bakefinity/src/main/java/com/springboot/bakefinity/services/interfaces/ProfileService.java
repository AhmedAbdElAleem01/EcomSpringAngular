package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;

public interface ProfileService {
    UserDTO getUserProfile(int userId);
    AddressDTO getAddress(int userId);
    UserDTO updateCreditLimit(int userId , Double newCreditLimit);
    String updateShippingInfo(int addressId , AddressDTO address);
    UserDTO updateAccountDetails(int id, UserDTO user);
    String changePassword(int id, String oldPass, String newPass);
    boolean isUsernameTaken(String username);
}