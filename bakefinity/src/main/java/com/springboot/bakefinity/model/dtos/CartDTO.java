package com.springboot.bakefinity.model.dtos;


import com.springboot.bakefinity.model.entities.CartItemId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class CartDTO implements Serializable {
    private Integer userId;
    private Integer productId;
    private int quantity;

    public CartDTO(CartItemId cartItemId, int quantity) {
        this.userId = cartItemId.getUserId();
        this.productId = cartItemId.getProductId();
        this.quantity = quantity;
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