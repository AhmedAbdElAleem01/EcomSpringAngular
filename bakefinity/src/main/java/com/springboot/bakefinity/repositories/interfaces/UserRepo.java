package com.bakefinity.controller.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import com.bakefinity.model.dtos.UserDTO;
import com.bakefinity.model.entities.User;

public interface UserRepo {
    public Optional<UserDTO> findByEmailAndPassword(String email ,String password);
    public Optional<UserDTO> findById(int id);
    int createUser(UserDTO user) throws SQLException;
    boolean isFoundUsername(String username) throws SQLException;
    boolean isFoundEmail(String email) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}
