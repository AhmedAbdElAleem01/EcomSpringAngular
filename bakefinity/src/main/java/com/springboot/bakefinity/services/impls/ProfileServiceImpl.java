package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.repositories.interfaces.ProfileRepo;
import com.springboot.bakefinity.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;


    @Override
    public Optional<UserDTO> getUserProfile(int userId) {
        return Optional.empty();
    }

    @Override
    public Optional<AddressDTO> getAddress(int userId) {
        return Optional.empty();
    }

    @Override
    public int updateCreditLimit(UserDTO user, Double newCreditLimit) {
        return profileRepo.updateCreditLimit(user.getId(), newCreditLimit);
    }

    @Override
    public Optional<UserDTO> updateShippingInfo(UserDTO user, String country, String city, String street, String BNo, String mobile) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> updateAccountDetails(UserDTO user, String username, String job, String email) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> updatePassword(UserDTO user, String currentPass, String newPass, String confirmPass) {
        return Optional.empty();
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return false;
    }
}
