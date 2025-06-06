package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.dtos.UserRegistrationRequestDTO;

import java.sql.SQLException;

public interface UserRegisterService {
    public void createUser(UserRegistrationRequestDTO request);
    boolean isUsernameAvailable(String username) ;
    boolean isEmailUnique(String email) ;
}