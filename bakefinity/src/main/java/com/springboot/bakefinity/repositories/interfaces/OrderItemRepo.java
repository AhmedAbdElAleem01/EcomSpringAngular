package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.entities.OrderItem;
import java.sql.SQLException;

public interface OrderItemRepo {
    boolean create(OrderItem orderItem) throws SQLException;
}
