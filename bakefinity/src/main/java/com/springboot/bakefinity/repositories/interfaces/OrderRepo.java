package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.dtos.OrderItemDTO;
import com.springboot.bakefinity.model.entities.Order;
import com.springboot.bakefinity.model.enums.OrderStatus;

import java.sql.SQLException;
import java.util.List;

public interface OrderRepo{
    int create(OrderDTO order) throws SQLException;
    boolean updateStatus(int orderId, OrderStatus orderStatus) throws SQLException;
    public Order get(int id) throws Exception; 
    List<Order> getOrdersByCustomerId(int customerId) throws SQLException;
    List<OrderItemDTO> getOrderItemsByOrderId(int orderId) throws SQLException;
}
