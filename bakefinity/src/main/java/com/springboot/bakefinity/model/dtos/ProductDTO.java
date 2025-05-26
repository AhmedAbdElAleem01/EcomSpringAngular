package com.bakefinity.model.dtos;

import com.bakefinity.model.entities.Product;

public class ProductDTO {
    private int id;
    private String name;
    private int categoryId;
    private String categoryName;
    private String description;
    private Double price;
    private String imageUrl;
    private int stockQuantity;
    private String ingredients;

    public ProductDTO(){}

    public ProductDTO(String name, String description, Double price, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDTO(int id, String name, String description, Double price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public ProductDTO(int categoryId, String description, Double price, String imageUrl, int stockQuantity, String ingredients, String name) {
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.ingredients = ingredients;
        this.name = name;
    }

    public ProductDTO(int id, String name, int categoryId, String description, Double price, String imageUrl, int stockQuantity, String ingredients) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stockQuantity = stockQuantity;
        this.ingredients = ingredients;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.categoryId = product.getCategory().getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.imageUrl = product.getImageUrl();
        this.ingredients = product.getIngredients();
        this.stockQuantity = product.getStockQuantity();
    }
    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public String getCategoryName() { return categoryName; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getIngredients() { return ingredients; }
    public void setIngredients(String ingredients) { this.ingredients = ingredients; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    @Override
    public String toString() {
        return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
                + ", imageUrl=" + imageUrl + ", stockQuantity=" + stockQuantity + "]";
    }    
    
}
