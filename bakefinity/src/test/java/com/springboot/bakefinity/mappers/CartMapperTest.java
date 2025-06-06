package com.springboot.bakefinity.mappers;

import com.springboot.bakefinity.model.dtos.CartDTO;
import com.springboot.bakefinity.model.entities.CartItem;
import com.springboot.bakefinity.model.entities.CartItemId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CartMapperTest {
    @Autowired
    CartMapper cartMapper;
    @Test
    void toDtoTest()
    {
        CartItem cartItem = new CartItem(new CartItemId(123, 456), 2);
        CartDTO cartDTO=new CartDTO(new CartItemId(123, 456), 2);
        assertThat(cartMapper.toDto(cartItem)).isEqualTo(cartDTO);


    }
    @Test
    void toDtoListTest()
    {
        List<CartItem> cartItem = List.of(new CartItem(new CartItemId(123, 456), 2),new CartItem(new CartItemId(10, 6), 8),new CartItem(new CartItemId(13, 41), 9));
        List<CartDTO> cartDTO=List.of(new CartDTO(new CartItemId(123, 456), 2),new CartDTO(new CartItemId(10, 6), 8),new CartDTO(new CartItemId(13, 41), 9));
        assertThat(cartMapper.toDtoList(cartItem)).isEqualTo(cartDTO);


    }
    @Test
    void toEntityTest()
    {
        CartItem cartItem = new CartItem(new CartItemId(123, 456), 2);
        CartDTO cartDTO=new CartDTO(new CartItemId(123, 456), 2);
        System.out.println(cartMapper.toEntity(cartDTO));
        assertTrue((cartMapper.toEntity(cartDTO)).equals(cartItem));


    }
    @Test
    void toEntityListTest()
    {
        List<CartItem> cartItem = List.of(new CartItem(new CartItemId(123, 456), 2),new CartItem(new CartItemId(10, 6), 8),new CartItem(new CartItemId(13, 41), 9));
        List<CartDTO> cartDTO=List.of(new CartDTO(new CartItemId(123, 456), 2),new CartDTO(new CartItemId(10, 6), 8),new CartDTO(new CartItemId(13, 41), 9));
        assertThat(cartMapper.toEntityList(cartDTO)).isEqualTo(cartItem);


    }

}