package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.mappers.CategoryMapper;
import com.springboot.bakefinity.model.dtos.CategoryDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.model.entities.UserInterest;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import com.springboot.bakefinity.services.interfaces.CategoryService;
import com.springboot.bakefinity.services.interfaces.UserInterestsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepository;
    private final CategoryMapper categoryMapper;
    private final UserInterestsService userInterestsService;

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


    @Override
    public Set<CategoryDTO> getUserInterestedCategories(int userId){
        Set<CategoryDTO> cats = new HashSet<>();

        Set<UserInterest> userInterestsById = userInterestsService.getUserInterestsById(userId);

        for (UserInterest userInterest : userInterestsById) {
            CategoryDTO category = this.getCategoryById(userInterest.getCategory().getId());
            if (category != null) {
                cats.add(category);
            }
        }
        return cats;
    }
}