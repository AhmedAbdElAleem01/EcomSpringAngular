package com.springboot.bakefinity.model.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InterestsId implements Serializable {
    private Integer userId;
    private Integer categoryId;

    public InterestsId(Integer userId, Integer categoryId) {
        this.userId = userId;
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "InterestsId{" +
                "userId=" + userId +
                ", categoryId=" + categoryId +
                '}';
    }

    public InterestsId() {
    }


    @Column(name = "userId", nullable = false)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    @Column(name = "categoryId", nullable = false)
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InterestsId that = (InterestsId) o;
        return Objects.equals(userId, that.userId) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, categoryId);
    }
}
