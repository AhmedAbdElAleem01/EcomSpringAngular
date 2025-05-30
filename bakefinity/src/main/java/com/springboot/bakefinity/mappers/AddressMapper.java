package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.entities.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressDTO toDTO(Address address);
    Address toEntity(AddressDTO addressDTO);
}