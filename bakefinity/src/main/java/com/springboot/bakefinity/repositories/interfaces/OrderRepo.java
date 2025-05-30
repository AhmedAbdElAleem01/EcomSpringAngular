package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE orders SET status = :status WHERE id = :orderId", nativeQuery = true)
    int updateStatus(int orderId, @Param("status") String orderStatus);

    List<Order> findByUser_Id(int userId);
}