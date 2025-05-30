package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.Product;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @NonNull
    List<Product> findAll();

    List<Product> findByCategoryId(int categoryId);

    List<Product> findAllByOrderByStockQuantityDesc(Pageable pageable);

    List<Product> findByNameContainingIgnoreCase(String name);


    @NonNull
    Page<Product> findAll(@NonNull Pageable pageable);

    Page<Product> findByCategoryId(int categoryId, Pageable pageable);

    int countByCategoryId(int categoryId);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.stockQuantity = :newQuantity WHERE p.id = :productId")
    int updateStockQuantity(@Param("productId") int productId, @Param("newQuantity") int newQuantity);
}