package com.springboot.bakefinity.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.bakefinity.mappers.UserMapper;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.User;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserDTO> getAllCustomers() {              
        List<User> users = userRepo.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDto(user));
        }
        return userDTOs;
    }

    @Override
    public UserDTO getCustomerById(int id) {
        return userMapper.toDto(userRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found")));
    }

    @Override
    public List<UserDTO> searchUsersByUsername(String usernamePart) {
        List<User> users = userRepo.findByUsernameContainingIgnoreCase(usernamePart);
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(userMapper.toDto(user));
        }
        return userDTOs;
    }
    
}
