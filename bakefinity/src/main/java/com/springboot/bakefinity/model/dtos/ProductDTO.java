package com.springboot.bakefinity.model.dtos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
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
    @Override
    public String toString() {
        return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
                + ", imageUrl=" + imageUrl + ", stockQuantity=" + stockQuantity + "]";
    }    
    
}
