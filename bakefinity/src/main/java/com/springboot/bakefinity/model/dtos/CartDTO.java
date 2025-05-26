package com.bakefinity.model.dtos;

import com.bakefinity.model.entities.CartItemId;

public class CartDTO {
    private Integer userId;
    private Integer productId;
    private int quantity;

    public CartDTO(CartItemId cartItemId, int quantity) {
        this.userId = cartItemId.getUserId();
        this.productId = cartItemId.getProductId();
        this.quantity = quantity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public Integer getProductId() {
        return productId;
    }



    public void setProductId(Integer productId) {
        this.productId = productId;
    }



    @Override
    public String toString() {
        return "CartDTO [userId=" + userId + ", productId=" + productId + ", quantity="
                + quantity + "]";
    }

 
   
    

}
