package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.CategoryDTO;
import com.springboot.bakefinity.model.entities.Category;
import java.util.List;
import java.util.Set;

public interface CategoryService {
    public List<CategoryDTO> getAllCategories();
    public CategoryDTO getCategoryById(int id);
    public CategoryDTO getCategoryByName(String name);
    Set<CategoryDTO> getUserInterestedCategories(int userId);
}