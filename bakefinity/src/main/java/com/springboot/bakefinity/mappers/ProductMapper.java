package com.springboot.bakefinity.mappers;
import org.mapstruct.Mapper;

import com.springboot.bakefinity.model.dtos.ProductDTO;
import com.springboot.bakefinity.model.entities.Product;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mappings({
        @Mapping(source = "category.id", target = "categoryId"),
        @Mapping(source = "category.name", target = "categoryName")
    })
    ProductDTO toDto(Product product);

    @Mappings({
        @Mapping(target = "cartItems", ignore = true),
        @Mapping(target = "category", ignore = true),
        @Mapping(target = "orderItems", ignore = true),
        @Mapping(target = "id", ignore = true)
    })
    Product toEntity(ProductDTO dto);
}