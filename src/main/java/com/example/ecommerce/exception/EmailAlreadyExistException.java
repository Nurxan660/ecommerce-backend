package com.example.ecommerce.exception;

public class EmailAlreadyExistException extends Exception{
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
