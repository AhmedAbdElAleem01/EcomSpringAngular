package com.springboot.bakefinity.controllers;

import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.dtos.CartItemDetailsDTO;
import com.springboot.bakefinity.services.impls.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @GetMapping("/{id}")
    public ResponseEntity<List<CartItemDetailsDTO>> getCartProducts(@PathVariable int id)
    {
        return ResponseEntity.ok(cartService.getCartItems(id));
    }
    @PostMapping
    public CartItemDetailsDTO addCartProduct(@RequestBody CartDTO cartDTO)
    {
        return cartService.addCartItem(cartDTO);

    }
    @PutMapping
    public CartItemDetailsDTO updateCartItem(@RequestBody CartDTO cartDTO)
    {
        return cartService.updateCartItem(cartDTO);


    }
    @DeleteMapping("/{userId}/{productId}")
    public String deleteItem(@PathVariable("userId")int uid,@PathVariable("productId")int pid)
    {
        cartService.removeCartItem(uid,pid);
        return "Deleted cart item successfully";
    }
    @DeleteMapping("/{id}")
    public String clearCart(@PathVariable int id)
    {
        cartService.clearCart(id);
        return "Cleared cart successfully";
    }



}
