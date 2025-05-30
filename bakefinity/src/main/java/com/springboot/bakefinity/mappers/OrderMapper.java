package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mappings({
        @Mapping(source = "user.id", target = "userId")
    })
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}