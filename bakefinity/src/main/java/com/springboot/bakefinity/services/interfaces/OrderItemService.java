package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.OrderItemDTO;
import com.springboot.bakefinity.model.entities.OrderItem;

import java.util.List;

public interface OrderItemService {
    OrderItem create(OrderItem orderItem);
    List<OrderItemDTO> getOrderItemByOrderId(int orderId);
}