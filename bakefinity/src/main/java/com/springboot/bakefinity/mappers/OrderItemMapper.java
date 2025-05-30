package com.springboot.bakefinity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import com.springboot.bakefinity.model.dtos.OrderItemDTO;
import com.springboot.bakefinity.model.entities.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mappings({
        @Mapping(source = "order.id", target = "orderId"),
        @Mapping(source = "order.user.id", target = "userId"),
        @Mapping(source = "order.user.username", target = "userName"),
        @Mapping(source = "product.id", target = "productId"),
        @Mapping(source = "product.name", target = "productName"),
        @Mapping(source = "product.price", target = "price")
    })
    OrderItemDTO toDTO(OrderItem orderItem);

    @Mappings({        
        @Mapping(target = "product", ignore = true),
        @Mapping(target = "order", ignore = true)
    })
    OrderItem toEntity(OrderItemDTO orderItemDTO);
}