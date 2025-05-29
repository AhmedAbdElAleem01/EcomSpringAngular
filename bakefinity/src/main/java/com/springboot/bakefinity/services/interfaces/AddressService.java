package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import java.sql.SQLException;

public interface AddressService {
    boolean createAddress(AddressDTO address) throws SQLException;
}