package com.springboot.bakefinity.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.dtos.OrderItemDTO;
import com.springboot.bakefinity.services.interfaces.OrderItemService;
import com.springboot.bakefinity.services.interfaces.OrderService;

@RestController
public class AdminOrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping("/admin/orders")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/admin/customers/{id}/orders")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable int id) {
        List<OrderDTO> orders = orderService.getAllOrdersByCustomerId(id);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/admin/orders/{id}")
    public ResponseEntity<List<OrderItemDTO>> getOrderById(@PathVariable int id) {
        List<OrderItemDTO> orderItems = orderItemService.getOrderItemByOrderId(id);
        return ResponseEntity.ok(orderItems);
    } 
    
}
