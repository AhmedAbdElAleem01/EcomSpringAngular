package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.entities.User;
import java.util.List;

public interface CustomerService {
    List<User> getAllCustomers();
    
} 
