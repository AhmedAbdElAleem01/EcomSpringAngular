package com.springboot.bakefinity.mappers;


import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.dtos.CartItemDetailsDTO;
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

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(source = "product.stockQuantity", target = "available_quantity")
    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.price", target = "unit_price")
    @Mapping(source = "product.imageUrl", target = "image_url")
    @Mapping(source = "product.description", target = "description")
    @Mapping(expression = "java(cartItem.getQuantity() * cartItem.getProduct().getPrice())",
            target = "totalPricePerProduct")
    @Mapping(source = "product.id", target = "productId")

    CartItemDetailsDTO toCartItemDetailsDTO(CartItem cartItem);
    List<CartItemDetailsDTO> toCartItemDetailsDTOList(List<CartItem> cartItems);

}