package com.springboot.bakefinity.repositories.interfaces;
import com.springboot.bakefinity.model.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    public Optional<User> findByEmailAndPassword(String email , String password);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findByUsernameContainingIgnoreCase(String usernamePart);
}
