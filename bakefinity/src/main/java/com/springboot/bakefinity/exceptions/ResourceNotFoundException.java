package com.springboot.bakefinity.exceptions;



// This class represents a custom exception that is thrown when a requested resource is not found.
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
