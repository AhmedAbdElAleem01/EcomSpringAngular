package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.UserDTO;
import java.util.List;

public interface CustomerService {
    List<UserDTO> getAllCustomers();
    UserDTO getCustomerById(int id);
    List<UserDTO> searchUsersByUsername(String usernamePart);    
} 
