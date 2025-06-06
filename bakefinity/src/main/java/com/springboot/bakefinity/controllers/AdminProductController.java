package com.springboot.bakefinity.controllers;

import java.util.List;

import com.springboot.bakefinity.model.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.bakefinity.exceptions.ValidationException;
import com.springboot.bakefinity.model.dtos.ProductDTO;
import com.springboot.bakefinity.services.interfaces.ProductService;

@RestController
@RequestMapping("/admin/products")
public class AdminProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> addProduct(@ModelAttribute("product") ProductDTO product, @RequestParam("product.image") MultipartFile image) {
        try {
            ProductDTO createdProduct = productService.addProduct(product, image);
            return ResponseEntity.ok(createdProduct);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping(path = "update/{id}",consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @ModelAttribute ProductDTO product) {
        try {
            ProductDTO updatedProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updatedProduct);
        } catch (ValidationException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("deleted");
    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProductsByName(@RequestParam String productName) {
        List<ProductDTO> products = productService.searchProductsByName(productName);
        return ResponseEntity.ok(products);
    }
    
    
}
