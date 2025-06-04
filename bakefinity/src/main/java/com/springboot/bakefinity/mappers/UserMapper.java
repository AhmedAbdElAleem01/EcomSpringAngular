package com.springboot.bakefinity.mappers;



import com.springboot.bakefinity.model.dtos.UserDTO;
import com.springboot.bakefinity.model.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
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
}
