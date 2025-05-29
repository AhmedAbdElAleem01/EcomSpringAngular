package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.dtos.OrderItemDTO;
import com.springboot.bakefinity.model.entities.Order;
import com.springboot.bakefinity.model.entities.OrderItem;

public interface OrderItemMapper {
    OrderItemDTO toDTO(OrderItem orderItem);
    OrderItem toEntity(OrderItemDTO orderItemDTO);
}