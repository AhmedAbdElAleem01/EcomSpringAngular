package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.UserDTO;

import java.sql.SQLException;

public interface UserRegisterService {
    int createUser(UserDTO user) throws SQLException;
    boolean isUsernameAvailable(String username) throws SQLException;
    boolean isEmailUnique(String email) throws SQLException;
}