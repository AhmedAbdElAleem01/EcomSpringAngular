package com.springboot.bakefinity.controllers;

import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.dtos.CartItemDetailsDTO;
import com.springboot.bakefinity.services.impls.CartService;
import com.springboot.bakefinity.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @GetMapping()
    public ResponseEntity<List<CartItemDetailsDTO>> getCartProducts()
    {
        int id= SecurityUtil.getCurrentUserId();
        return ResponseEntity.ok(cartService.getCartItems(id));
    }
    @PostMapping
    public CartItemDetailsDTO addCartProduct(@RequestBody CartDTO cartDTO)
    {
        cartDTO.setUserId(SecurityUtil.getCurrentUserId());
        return cartService.addCartItem(cartDTO);

    }
    @PutMapping
    public CartItemDetailsDTO updateCartItem(@RequestBody CartDTO cartDTO)
    {
        return cartService.updateCartItem(cartDTO);


    }
    @DeleteMapping("/{productId}")
    public String deleteItem(@PathVariable("productId")int pid)
    {
        int uid= SecurityUtil.getCurrentUserId();
        cartService.removeCartItem(uid,pid);
        return "Deleted cart item successfully";
    }
    @DeleteMapping()
    public String clearCart()
    {
        int id= SecurityUtil.getCurrentUserId();
        cartService.clearCart(id);
        return "Cleared cart successfully";
    }



}
