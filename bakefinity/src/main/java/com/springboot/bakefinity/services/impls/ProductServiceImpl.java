package com.springboot.bakefinity.services.impls;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.bakefinity.exceptions.ValidationException;
import com.springboot.bakefinity.mappers.ProductMapper;
import com.springboot.bakefinity.model.dtos.ProductDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.model.entities.Product;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import com.springboot.bakefinity.repositories.interfaces.ProductRepo;
import com.springboot.bakefinity.services.interfaces.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductMapper productMapper;

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @Override
    public List<ProductDTO> getAllProducts() {        
        List<Product> products = productRepo.getAllProducts();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(productMapper.toDto(product));
        }
        return productDTOs;
    }

    @Override
    public List<ProductDTO> getAllProductsWithCategoryName() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProductsWithCategoryName'");
    }

    @Override
    public List<ProductDTO> getProductsByCategory(int categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategory'");
    }

    @Override
    public List<ProductDTO> getClassicProducts(int limit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClassicProducts'");
    }

    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchProductsByName'");
    }

    @Override
    public ProductDTO getProductById(int id) {
        Product product = productRepo.findById(id)
            .orElseThrow(() -> new ValidationException("Product not found with id: " + id));
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getProductsByCategoryPage(int categoryId, int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategoryPage'");
    }

    @Override
    public int getTotalProductsByCategory(int categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalProductsByCategory'");
    }

    @Override
    public List<ProductDTO> getProductsByPage(int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByPage'");
    }

    @Override
    public int getTotalProductCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalProductCount'");
    }

    @Override
    public ProductDTO addProduct(ProductDTO product, MultipartFile image) throws ValidationException {
        // image upload
        String imageUrl = uploadProductImage(image);
        product.setImageUrl(imageUrl);  
        
        // Validation
        if (product.getName() == null || product.getName().trim().isEmpty()) 
            throw new ValidationException("Product name is required.");

        if (product.getPrice() == null || product.getPrice() <= 0)
            throw new ValidationException("Product price must be a positive number.");

        if (product.getStockQuantity() < 0)
            throw new ValidationException("Product stock quantity must be a non-negative number.");

        if (product.getCategoryName() == null)
            throw new ValidationException("Product category is required.");

        Category category = categoryRepo.findByName(product.getCategoryName())
                        .orElseThrow(() -> new RuntimeException("Category not found"));
        Product toSave = productMapper.toEntity(product);
        toSave.setCategory(category);
        
        return productMapper.toDto(productRepo.save(toSave)); 
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepo.findById(id).orElseThrow(
            () -> new ValidationException("Product not found with id: " + id));
        productRepo.deleteById(id);
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO updatedProduct) throws ValidationException {
        Product existingProduct = productRepo.findById(id)
        .orElseThrow(() -> new ValidationException("Product not found with id: " + id));

        // Validation
        if (updatedProduct.getName() == null || updatedProduct.getName().trim().isEmpty()) 
            throw new ValidationException("Product name is required.");

        if (updatedProduct.getPrice() == null || updatedProduct.getPrice() < 0)
            throw new ValidationException("Product price must be a positive number.");

        if (updatedProduct.getStockQuantity() < 0)
            throw new ValidationException("Product stock quantity must be a non-negative number.");

        if (updatedProduct.getCategoryName() == null)
            throw new ValidationException("Product category is required.");

        // Update 
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());

        Product saved = productRepo.save(existingProduct);
        return productMapper.toDto(saved);
    }

    @Override
    public boolean updateStockQuantity(int productId, int newQuantity) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStockQuantity'");
    }

    @Override
    public List<ProductDTO> getProductsByCategoryAndPriceRange(int categoryId, double minPrice, double maxPrice,
            int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByCategoryAndPriceRange'");
    }

    @Override
    public List<ProductDTO> getProductsByPriceRange(double minPrice, double maxPrice, int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductsByPriceRange'");
    }

    @Override
    public int getTotalProductsByPrice(double minPrice, double maxPrice, Integer categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTotalProductsByPrice'");
    }
    private String uploadProductImage(MultipartFile image) {
        try {
            File uploadPath = new File(UPLOAD_DIRECTORY);
            if (!uploadPath.exists()) uploadPath.mkdirs();
            
            StringBuilder fileNames = new StringBuilder();
            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, image.getOriginalFilename());
            fileNames.append(image.getOriginalFilename());
            Files.write(fileNameAndPath, image.getBytes());   //write

            return fileNames.toString();

        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }
}
