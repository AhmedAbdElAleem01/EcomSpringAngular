package com.springboot.bakefinity.services.interfaces;

import com.springboot.bakefinity.exceptions.ValidationException;
import com.springboot.bakefinity.model.dtos.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsByCategory(int categoryId);

    List<ProductDTO> getClassicProducts(int limit) ;
    List<ProductDTO> searchProductsByName(String name);

    ProductDTO getProductById(int id);

    Page<ProductDTO> getProductsByCategoryPaged(int categoryId, int page, int pageSize);
    int getTotalProductsByCategory(int categoryId);

    Page<ProductDTO> getProductsByPage(int page, int pageSize, Sort sort);
    long getTotalProductCount();

    ProductDTO addProduct(ProductDTO product, MultipartFile image) throws ValidationException;
    void deleteProduct(int id);
    ProductDTO updateProduct(int id, ProductDTO updatedProduct) throws ValidationException;

    boolean updateStockQuantity(int productId, int newQuantity) throws SQLException;


}