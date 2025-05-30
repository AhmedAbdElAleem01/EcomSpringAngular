package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.Category;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    List<Category> findByNameContainingIgnoreCase(String name);

    Optional<Category> findByName(@NonNull String name);
}