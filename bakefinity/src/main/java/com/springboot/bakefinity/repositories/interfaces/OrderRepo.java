package com.bakefinity.controller.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bakefinity.model.dtos.OrderDTO;
import com.bakefinity.model.dtos.OrderItemDTO;
import com.bakefinity.model.entities.Order;
import com.bakefinity.model.enums.OrderStatus;

public interface OrderRepo{
    int create(OrderDTO order) throws SQLException;
    boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException;
    public Order get(int id) throws Exception; 
    List<Order> getOrdersByCustomerId(int customerId) throws SQLException;
    List<OrderItemDTO> getOrderItemsByOrderId(int orderId) throws SQLException;
}
