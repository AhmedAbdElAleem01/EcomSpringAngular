package com.bakefinity.controller.repositories.interfaces;

import java.util.Optional;

import com.bakefinity.model.dtos.UserDTO;

public interface AdminRepo {
    public Optional<UserDTO> findByEmailAndPassword(String email ,String password); //login as admin
}

