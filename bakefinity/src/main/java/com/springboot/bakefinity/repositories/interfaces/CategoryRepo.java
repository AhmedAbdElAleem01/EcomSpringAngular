package com.bakefinity.controller.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.bakefinity.model.dtos.CategoryDTO;
import com.bakefinity.model.entities.Category;

public interface CategoryRepo{
    Category get(int id) throws Exception;
    List<Category> getAll() throws Exception;
    List<Category> searchByName(String name) throws Exception;
    CategoryDTO getCategoryByName(String categoryName) throws SQLException;

}