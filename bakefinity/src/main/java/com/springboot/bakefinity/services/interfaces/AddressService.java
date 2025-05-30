package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.entities.Address;

import java.sql.SQLException;
import java.util.Optional;

public interface AddressService {
    AddressDTO createAddress(AddressDTO address);
    Optional<AddressDTO> getAddressByUserId(int userId);
}