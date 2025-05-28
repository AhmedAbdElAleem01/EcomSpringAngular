package com.springboot.bakefinity.repositories.interfaces;

import com.springboot.bakefinity.model.dtos.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepo extends BaseRepo<ProductDTO>{
    List<ProductDTO> getAllProducts() throws Exception;
    List<ProductDTO> getByCategory(int categoryId) throws Exception;

    List<ProductDTO> getTopInStock(int limit) throws Exception;

    List<ProductDTO> searchByName(String name) throws Exception;

    List<ProductDTO> getProductsByPage(int offset, int limit) throws Exception;
    List<ProductDTO> getProductsByCategoryPage(int categoryId, int offset, int limit) throws Exception;

    int getTotalCount() throws Exception;
    int getTotalCountByCategory(int categoryId) throws Exception;

    boolean updateStockQuantity(int productId, int newQuantity) throws SQLException;
    ProductDTO getById(int productId) throws SQLException;

    
    int getTotalProductsByPrice(Double minPrice, Double maxPrice, Integer categoryId) throws Exception;
    List<ProductDTO> getProductsByPriceRange(int offset, int limit, double minPrice, double maxPrice)throws Exception;
    List<ProductDTO> getProductsByCategoryAndPriceRange(int categoryId, double minPrice, double maxPrice, int offset, int limit)throws Exception;
}