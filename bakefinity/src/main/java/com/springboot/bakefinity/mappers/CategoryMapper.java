package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.CategoryDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDto(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
