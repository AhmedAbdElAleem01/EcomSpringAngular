package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.AddressDTO;

import java.sql.SQLException;
import java.util.Optional;

public interface AddressRepo {
    boolean createAddress(AddressDTO address) throws SQLException;
    public Optional<AddressDTO> findUserAddressById(int id);
}
