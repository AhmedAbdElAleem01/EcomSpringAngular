package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.mappers.OrderMapper;
import com.springboot.bakefinity.mappers.UserMapper;
import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.Order;
import com.springboot.bakefinity.model.enums.OrderStatus;
import com.springboot.bakefinity.repositories.interfaces.OrderRepo;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;


    @Override
    public int create(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        order.setUser(userRepo.findById(orderDTO.getUserId()).orElse(null));
        Order savedOrder = orderRepo.save(order); // save => can accept an Entity object only and can return S extends Entity or Entity itself
        return savedOrder != null ? orderMapper.toDTO(savedOrder).getId() : -1;
    }

    @Override
    public int updateStatus(int orderId, OrderStatus orderStatus){
        return orderRepo.updateStatus(orderId, orderStatus.name());
    }

    @Override
    public List<OrderDTO> getAllOrdersByCustomerId(int userId) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        List<Order> orders = orderRepo.findByUser_Id(userId);
        for (Order order : orders) {
            orderDTOs.add(orderMapper.toDTO(order));
        }
        return orderDTOs;
    }


    /* just for testing */
    @Override
    public UserDTO getCurrentUser(int id) {
        return userMapper.toDto(userRepo.findById(id).orElse(null));
    }
}