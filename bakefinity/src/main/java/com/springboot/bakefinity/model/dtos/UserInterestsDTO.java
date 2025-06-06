package com.springboot.bakefinity.model.dtos;

import org.springframework.stereotype.Component;


public class UserInterestsDTO {
    private int userId;
    private int categoryId;

    public UserInterestsDTO(int userId, int categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "UserInterests{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }
}
