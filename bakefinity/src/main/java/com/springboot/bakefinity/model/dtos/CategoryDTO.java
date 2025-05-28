package com.springboot.bakefinity.model.dtos;

import com.springboot.bakefinity.model.entities.Category;

public class CategoryDTO {
    private int id;
    private String name;
    private String description;
    private String imageUrl;

    public CategoryDTO(){}

    public CategoryDTO(int id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public CategoryDTO(Category c) {
        this.id = c.getId();
        this.name = c.getName();
        this.description = c.getDescription();
        this.imageUrl = c.getImageUrl();
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CategoryDTO [name=" + name + ", description=" + description + ", imageUrl=" + imageUrl + "]";
    }
}
