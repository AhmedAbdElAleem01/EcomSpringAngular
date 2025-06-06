package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.User;
import com.springboot.bakefinity.model.enums.OrderStatus;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    int create(OrderDTO order);
    int updateStatus(Integer orderId, OrderStatus orderStatus);
    List<OrderDTO> getAllOrdersByCustomerId(int userId);
    UserDTO getUserById(Integer userId);
}