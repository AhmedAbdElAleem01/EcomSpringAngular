package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.OrderDTO;
import com.springboot.bakefinity.model.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring") /** componentModel => It controls how the generated mapper implementation is managed or instantiated **/
public interface OrderMapper {
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}