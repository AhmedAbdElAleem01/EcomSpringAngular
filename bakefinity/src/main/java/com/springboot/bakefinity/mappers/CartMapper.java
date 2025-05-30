package com.springboot.bakefinity.mappers;


import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mappings({
            @Mapping(source = "id.userId", target = "userId"),
            @Mapping(source = "id.productId", target = "productId"),
            @Mapping(source = "quantity", target = "quantity")
    })
    CartDTO toDto(CartItem cartItem);

    List<CartDTO> toDtoList(List<CartItem> cartItems);

    @Mappings({
            @Mapping(source = "userId", target = "id.userId"),
            @Mapping(source = "productId", target = "id.productId"),
            @Mapping(source = "quantity", target = "quantity")
    })
    CartItem toEntity(CartDTO cartDTO);

    List<CartItem> toEntityList(List<CartDTO> cartDTO);
}