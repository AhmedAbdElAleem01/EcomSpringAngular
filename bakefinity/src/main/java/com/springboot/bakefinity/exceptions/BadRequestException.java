package com.springboot.bakefinity.exceptions;

//Thrown when the client sends an invalid or malformed request.ddata field missing
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
