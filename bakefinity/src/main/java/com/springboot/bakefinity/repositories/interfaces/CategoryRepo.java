package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.CategoryDTO;
import com.springboot.bakefinity.model.entities.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryRepo{
    Category get(int id) throws Exception;
    List<Category> getAll() throws Exception;
    List<Category> searchByName(String name) throws Exception;
    CategoryDTO getCategoryByName(String categoryName) throws SQLException;

}