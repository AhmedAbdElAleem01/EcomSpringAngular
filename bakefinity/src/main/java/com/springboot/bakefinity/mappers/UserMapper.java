package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.UserRegistrationRequestDTO;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.User;
import java.time.LocalDateTime;
import java.util.Date;

@Mapper(componentModel = "spring",imports = {LocalDateTime.class})
public interface UserMapper {
    @Mappings({
            @Mapping(target = "cartItems", ignore = true),
            @Mapping(target = "categories", ignore = true),
            @Mapping(target = "addresses", ignore = true),
            @Mapping(target = "orders", ignore = true),
            @Mapping(target = "id", ignore = true)
    })
    User toEntity(UserDTO user);

    UserDTO toDto(User user);

   // @Mapping(target = "username", expression = "java(dto.getFname() + \" \" + dto.getLname())")
    @Mapping(target = "name", expression = "java(dto.getFname() + \" \" + dto.getLname())")
    @Mapping(target = "creditLimit", expression = "java(Double.parseDouble(dto.getCreditLimit()))")
    @Mapping(target = "birthDate", source = "birthDate")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())") // Updated fully qualified name
    @Mapping(target = "password", source = "hashedPassword")
    @Mapping(target = "cartItems", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toUser(UserRegistrationRequestDTO dto, Date birthDate, String hashedPassword);



}
