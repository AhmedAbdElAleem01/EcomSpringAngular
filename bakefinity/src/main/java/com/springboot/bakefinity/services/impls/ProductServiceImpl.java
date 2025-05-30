package com.springboot.bakefinity.services.impls;

import com.springboot.bakefinity.exceptions.ResourceNotFoundException;
import com.springboot.bakefinity.exceptions.ValidationException;
import com.springboot.bakefinity.mappers.ProductMapper;
import com.springboot.bakefinity.model.dtos.ProductDTO;
import com.springboot.bakefinity.model.entities.Category;
import com.springboot.bakefinity.model.entities.Product;
import com.springboot.bakefinity.repositories.interfaces.CategoryRepo;
import com.springboot.bakefinity.repositories.interfaces.ProductRepo;
import com.springboot.bakefinity.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryRepo categoryRepo;

    private final static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductsByCategory(int categoryId) {
        boolean categoryExists = categoryRepo.existsById(categoryId);
        if (!categoryExists) {
            throw new ResourceNotFoundException("There is no category with ID " + categoryId);
        }
        return productRepo.findByCategoryId(categoryId)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getClassicProducts(int limit) {
        if(limit > productRepo.findAll().size()){
            throw new ValidationException("your limit exceeds size");
        }
        return productRepo.findAllByOrderByStockQuantityDesc(PageRequest.of(0, limit))
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> searchProductsByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public ProductDTO getProductById(int id) {
        return productMapper.toDto(
                productRepo.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("no such product"))
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductDTO> getProductsByCategoryPaged(int categoryId, int page, int pageSize) {
        boolean categoryExists = categoryRepo.existsById(categoryId);
        if (!categoryExists) {
            throw new ResourceNotFoundException("There is no category with ID " + categoryId);
        }

        Pageable pageable = PageRequest.of(page, pageSize);
        return productRepo.findByCategoryId(categoryId, pageable)
                .map(productMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public int getTotalProductsByCategory(int categoryId) {
        boolean categoryExists = categoryRepo.existsById(categoryId);
        if (!categoryExists) {
            throw new ResourceNotFoundException("There is no category with ID " + categoryId);
        }
        return productRepo.countByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductDTO> getProductsByPage(int page, int pageSize, Sort sort) {
        return productRepo.findAll(PageRequest.of(page, pageSize, sort))
                .map(productMapper::toDto);
    }

    @Transactional(readOnly = true)
    @Override
    public long getTotalProductCount() {
        return productRepo.count();
    }

    @Transactional
    @Override
    public ProductDTO addProduct(ProductDTO product, MultipartFile image) throws ValidationException {
        String imageUrl = uploadProductImage(image);
        product.setImageUrl(imageUrl);

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

    @Transactional
    @Override
    public void deleteProduct(int id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ValidationException("Product not found with id: " + id));
        productRepo.deleteById(id);
    }

    @Transactional
    @Override
    public ProductDTO updateProduct(int id, ProductDTO updatedProduct) throws ValidationException {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ValidationException("Product not found with id: " + id));

        if (updatedProduct.getName() == null || updatedProduct.getName().trim().isEmpty())
            throw new ValidationException("Product name is required.");

        if (updatedProduct.getPrice() == null || updatedProduct.getPrice() < 0)
            throw new ValidationException("Product price must be a positive number.");

        if (updatedProduct.getStockQuantity() < 0)
            throw new ValidationException("Product stock quantity must be a non-negative number.");

        if (updatedProduct.getCategoryName() == null)
            throw new ValidationException("Product category is required.");

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());

        Product saved = productRepo.save(existingProduct);
        return productMapper.toDto(saved);
    }

    @Transactional
    @Override
    public boolean updateStockQuantity(int productId, int newQuantity) {
        int updatedRows = productRepo.updateStockQuantity(productId, newQuantity);
        if (updatedRows == 0) {
            throw new ResourceNotFoundException("Product with id " + productId + " not found");
        }
        return true;
    }

    private String uploadProductImage(MultipartFile image) {
        try {
            File uploadPath = new File(UPLOAD_DIRECTORY);
            if (!uploadPath.exists()) uploadPath.mkdirs();

            Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, image.getOriginalFilename());
            Files.write(fileNameAndPath, image.getBytes());   // write

            return image.getOriginalFilename();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }
}
