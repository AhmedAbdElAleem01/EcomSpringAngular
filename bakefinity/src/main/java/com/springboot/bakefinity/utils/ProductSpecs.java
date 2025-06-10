package com.springboot.bakefinity.utils;

import com.springboot.bakefinity.model.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecs {
    public static Specification<Product> isNotDeleted() {
        return (root, query, cb) -> cb.isFalse(root.get("deleted"));
    }

    public static Specification<Product> categoryEquals(Integer categoryId) {
        return (root, query, cb) -> cb.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> priceBetween(Double minPrice, Double maxPrice) {
        if (minPrice != null && maxPrice != null) {
            return (root, query, cb) -> cb.between(root.get("price"), minPrice, maxPrice);
        } else if (minPrice != null) {
            return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), minPrice);
        } else {
            return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        }
    }
}

