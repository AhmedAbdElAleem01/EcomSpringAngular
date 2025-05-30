package com.springboot.bakefinity.model.dtos;


import com.springboot.bakefinity.model.entities.CartItemId;

public class CartDTO {
    private Integer userId;
    private Integer productId;
    private int quantity;

    public CartDTO() {}
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
    public boolean equals(Object obj) {
        boolean result = false;
        if (obj instanceof CartDTO) {
            CartDTO cartDTO = (CartDTO) obj;
            if (this.userId.equals(cartDTO.getUserId()) && this.productId.equals(cartDTO.getProductId())&& this.quantity == cartDTO.getQuantity()) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "CartDTO [userId=" + userId + ", productId=" + productId + ", quantity="
                + quantity + "]";
    }

 
   
    

}
