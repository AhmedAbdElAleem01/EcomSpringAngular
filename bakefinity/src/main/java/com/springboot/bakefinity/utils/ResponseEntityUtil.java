package com.springboot.bakefinity.utils;

import org.springframework.http.ResponseEntity;

public class ResponseEntityUtil {
    public static <T> ResponseEntity<T> createOkResponse(T body) {
        return ResponseEntity.ok(body);
    }

    public static <T> ResponseEntity<T> createBadRequest(T body){
        return ResponseEntity.badRequest().body(body);
    }
}
