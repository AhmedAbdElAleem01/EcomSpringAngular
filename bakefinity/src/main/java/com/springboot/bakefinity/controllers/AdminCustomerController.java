package com.springboot.bakefinity.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.services.interfaces.CustomerService;

@RestController
@RequestMapping("/admin/customers")
public class AdminCustomerController {

    @Autowired
    private CustomerService customerService;
    
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllCustomers() {
        List<UserDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getCustomerById(@PathVariable int id) {
        try {
            UserDTO customer = customerService.getCustomerById(id);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchCustomersByUsername(@RequestParam String usernamePart) {
        List<UserDTO> customers = customerService.searchUsersByUsername(usernamePart);
        return ResponseEntity.ok(customers);
    }
}
