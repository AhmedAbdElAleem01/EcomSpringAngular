package com.springboot.bakefinity.model.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"description", "ingredients"})
public class ProductDTO {
    private Long id;
    private String name;
    private int categoryId;
    private String categoryName;
    private String description;
    private Double price;
    private String imageUrl;
    private int stockQuantity;
    private String ingredients;
}