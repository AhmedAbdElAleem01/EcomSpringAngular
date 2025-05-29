package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.model.dtos.ProductDTO;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getAllProductsWithCategoryName();
    List<ProductDTO> getProductsByCategory(int categoryId);

    List<ProductDTO> getClassicProducts(int limit) ;
    List<ProductDTO> searchProductsByName(String name);

    ProductDTO getProductById(int id);

    List<ProductDTO> getProductsByCategoryPage(int categoryId, int page, int pageSize);
    int getTotalProductsByCategory(int categoryId);

    List<ProductDTO> getProductsByPage(int page, int pageSize);
    int getTotalProductCount();
   
    boolean addNewProduct(String category, String name, String description, String price, String imageName,
            String quantity, String ingredients) throws Exception;
    boolean deleteProduct(int productId);
    boolean updateProduct(ProductDTO product,String category, String name, String description, String price, String imageName,
    String quantity, String ingredients) throws Exception;

    boolean updateStockQuantity(int productId, int newQuantity) throws SQLException;
    List<ProductDTO> getProductsByCategoryAndPriceRange(int categoryId, double minPrice, double maxPrice, int page, int pageSize);
    List<ProductDTO> getProductsByPriceRange(double minPrice, double maxPrice, int page, int pageSize);
    int getTotalProductsByPrice(double minPrice, double maxPrice, Integer categoryId);

}