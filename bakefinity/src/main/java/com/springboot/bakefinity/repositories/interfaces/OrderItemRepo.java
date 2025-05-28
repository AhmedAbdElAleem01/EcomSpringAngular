package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.entities.OrderItem;

import java.sql.SQLException;

public interface OrderItemRepo {
    boolean create(OrderItem orderItem) throws SQLException;
}
