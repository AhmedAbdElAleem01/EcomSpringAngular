package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.CategoryDTO;
import com.springboot.bakefinity.model.entities.Category;
import java.util.List;

public interface CategoryService {
    public List<CategoryDTO> getAllCategories();
    public Category getCategoryById(int id);
    public CategoryDTO getCategoryByName(String name);
}