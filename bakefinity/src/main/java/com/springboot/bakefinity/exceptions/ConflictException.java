package com.springboot.bakefinity.exceptions;


//Thrown for violations like trying to create a resource that already exists. adding user with existing email for example
public class ConflictException extends RuntimeException{
    public ConflictException(String message) {
        super(message);
    }
}
