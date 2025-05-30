package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.UserDTO;

import java.util.Optional;

public interface UserLoginService {
    public Optional<UserDTO> login(String email , String password);
}
