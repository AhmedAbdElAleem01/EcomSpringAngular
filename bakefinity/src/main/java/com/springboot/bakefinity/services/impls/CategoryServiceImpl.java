package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.mappers.CategoryMapper;
import com.springboot.bakefinity.model.dtos.CategoryDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import com.springboot.bakefinity.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Category not found with Name: " + name));
        return categoryMapper.toDto(category);
    }
}