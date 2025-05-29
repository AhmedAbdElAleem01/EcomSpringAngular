package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.enums.OrderStatus;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    int create(OrderDTO order) throws SQLException;
    boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException;
    List<OrderDTO> getAllOrdersByCustomerId(int userId);
}