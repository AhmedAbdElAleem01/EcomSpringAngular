package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.CartItem;
import com.springboot.bakefinity.model.entities.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepo extends JpaRepository<CartItem, CartItemId> {
    List<CartItem> findByIdUserId(int userId);

    @Query("SELECT c FROM CartItem c WHERE c.user.id = :userId AND c.product.id = :productId")
    Optional<CartItem> findByUserIdAndProductId(int userId, int productId);

    void deleteByIdUserIdAndIdProductId(int userId, int productId);

    void deleteByIdUserId(int userId);
}
