package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.exceptions.ResourceNotFoundException;
import com.springboot.bakefinity.exceptions.ValidationException;
import com.springboot.bakefinity.mappers.AddressMapper;
import com.springboot.bakefinity.mappers.UserMapper;
import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.Address;
import com.springboot.bakefinity.model.entities.User;
import com.springboot.bakefinity.repositories.interfaces.AddressRepo;
import com.springboot.bakefinity.repositories.interfaces.ProfileRepo;
import com.springboot.bakefinity.repositories.interfaces.UserRepo;
import com.springboot.bakefinity.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDTO getUserProfile(int userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found."));
        return userMapper.toDto(user);
    }

    @Override
    public AddressDTO getAddress(int userId) {
        Address address = addressRepo.findByUser_Id(userId)
                .orElseThrow(()->new ResourceNotFoundException("Address not found."));
        return addressMapper.toDTO(address);
    }

    @Override
    public UserDTO updateCreditLimit(int userId, Double newCreditLimit) {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found."));
        if(newCreditLimit < 0)
            throw new ValidationException("Your credit Limit should be positive number. ");
        user.setCreditLimit(newCreditLimit);
        return userMapper.toDto(userRepo.save(user));
    }

    @Override
    public String updateShippingInfo(int userId, AddressDTO address, String phoneNumber) {
        User updatedUser = userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        Address updatedAddress = addressRepo.findByUser_Id(userId)
                .orElseThrow(()->new ResourceNotFoundException("Address not found."));

        if(address.getCountry() == null || address.getCountry().trim().isBlank()
          || address.getStreet()== null || address.getStreet().trim().isBlank()
          || address.getCity()== null || address.getCity().trim().isBlank()
          || address.getBuildingNo()==null || phoneNumber==null || phoneNumber.trim().isBlank())
            throw new ValidationException("Your shipping Info should not be empty.");

        updatedUser.setPhoneNumber(phoneNumber);
        updatedAddress.setCountry(address.getCountry());
        updatedAddress.setStreet(address.getStreet());
        updatedAddress.setCity(address.getCity());

        userRepo.save(updatedUser);
        addressRepo.save(updatedAddress);

        return "Successfully updated shipping info.";
    }

    @Override
    public UserDTO updateAccountDetails(int id, String name, String username, String email, String job) {
        User updatedUser = userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found."));
        if(name == null || name.trim().isBlank()
        || username == null || username.trim().isBlank()
        || email == null || email.trim().isBlank())
            throw new ValidationException("Your account details should not be empty.");

        updatedUser.setName(name);
        updatedUser.setUsername(username);
        updatedUser.setEmail(email);
        updatedUser.setJob(job);
        return userMapper.toDto(userRepo.save(updatedUser));
    }

    @Override
    public String changePassword(int id, String oldPass, String newPass) {
        User user = userRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("User not found."));
        if (!oldPass.equals(user.getPassword()))
            throw new ValidationException("Wrong password.");
        user.setPassword(newPass);
        userRepo.save(user);
        return "Successfully updated the password.";
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return userRepo.existsByUsername(username);
    }
}
