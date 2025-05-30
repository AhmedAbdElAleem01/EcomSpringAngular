package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.mappers.AddressMapper;
import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.repositories.interfaces.AddressRepo;
import com.springboot.bakefinity.services.interfaces.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private AddressMapper addressMapper;


    @Override
    public AddressDTO createAddress(AddressDTO address) {
        return addressMapper.toDTO(addressRepo.save(addressMapper.toEntity(address)));
    }

    @Override
    public Optional<AddressDTO> getAddressByUserId(int userId) {
        return addressRepo.findByUser_Id(userId).map(addressMapper::toDTO);
    }
}