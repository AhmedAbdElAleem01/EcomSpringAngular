package com.springboot.bakefinity.exceptions;


//For access control/authorization failures.
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException(String message) {
        super(message);
    }

}
