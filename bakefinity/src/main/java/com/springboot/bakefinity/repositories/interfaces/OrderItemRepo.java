package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.OrderItem;
import com.springboot.bakefinity.model.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderItemRepo extends JpaRepository<OrderItem, OrderItemId> {
    List<OrderItem> findByOrder_Id(int orderId);
}