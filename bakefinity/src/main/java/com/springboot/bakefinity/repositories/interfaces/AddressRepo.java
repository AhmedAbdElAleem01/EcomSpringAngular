package com.bakefinity.controller.repositories.interfaces;

import com.bakefinity.model.dtos.AddressDTO;

import java.sql.SQLException;
import java.util.Optional;

public interface AddressRepo {
    boolean createAddress(AddressDTO address) throws SQLException;
    public Optional<AddressDTO> findUserAddressById(int id);
}
