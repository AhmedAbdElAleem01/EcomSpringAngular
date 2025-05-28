package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserRepo {
    public Optional<UserDTO> findByEmailAndPassword(String email , String password);
    public Optional<UserDTO> findById(int id);
    int createUser(UserDTO user) throws SQLException;
    boolean isFoundUsername(String username) throws SQLException;
    boolean isFoundEmail(String email) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}
