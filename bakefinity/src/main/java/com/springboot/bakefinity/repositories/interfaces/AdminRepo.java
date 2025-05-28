package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.UserDTO;

import java.util.Optional;

public interface AdminRepo {
    public Optional<UserDTO> findByEmailAndPassword(String email , String password); //login as admin
}

