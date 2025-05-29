package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.mappers.OrderItemMapper;
import com.springboot.bakefinity.model.dtos.OrderItemDTO;
import com.springboot.bakefinity.model.entities.OrderItem;
import com.springboot.bakefinity.repositories.interfaces.OrderItemRepo;
import com.springboot.bakefinity.services.interfaces.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public OrderItem create(OrderItem orderItem) {
        return orderItemRepo.save(orderItem);
    }

    @Override
    public List<OrderItemDTO> getOrderItemByOrderId(int orderId) {
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        List<OrderItem> orderItems = orderItemRepo.findByOrder_Id(orderId);
        for (OrderItem orderItem : orderItems){
            orderItemDTOS.add(orderItemMapper.toDTO(orderItem));
        }
        return orderItemDTOS;
    }
}