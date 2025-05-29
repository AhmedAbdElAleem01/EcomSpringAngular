package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.Category;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
    Optional<Category> findByName(String name);

}