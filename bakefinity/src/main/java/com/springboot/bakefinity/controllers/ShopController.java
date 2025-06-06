package com.springboot.bakefinity.controllers;


import com.springboot.bakefinity.model.dtos.CategoryDTO;
import com.springboot.bakefinity.model.dtos.ProductDTO;
import com.springboot.bakefinity.services.interfaces.CategoryService;
import com.springboot.bakefinity.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Sort;

import static com.springboot.bakefinity.utils.ResponseEntityUtil.*;

@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ProductService productService;

    private final CategoryService categoryService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> allProducts = productService.getAllProducts();
        return createOkResponse(allProducts);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return createOkResponse(categories);
    }

    @GetMapping("products/filter")
    public Page<ProductDTO> filterProducts(
            @RequestParam(required = false, name = "categoryId") Integer catID,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "id,asc") String sort
    ) {

        System.out.println("cat :  " +catID);

        String[] sortParams = sort.split(",");
        String sortField = sortParams[0].trim();

        Sort.Direction direction =
               sortParams.length <= 1 ? Sort.Direction.ASC : Sort.Direction.fromString(sortParams[1]);

        System.out.println(catID);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        System.out.println(productService.findAllFiltered(catID, minPrice, maxPrice, pageable).getContent());

        return productService.findAllFiltered(catID, minPrice, maxPrice, pageable);
    }

}
