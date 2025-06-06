package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.AddressDTO;
import com.springboot.bakefinity.model.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mappings({
        @Mapping(source = "user.id", target = "userId")
    })
    AddressDTO toDTO(Address address);
    Address toEntity(AddressDTO addressDTO);
}