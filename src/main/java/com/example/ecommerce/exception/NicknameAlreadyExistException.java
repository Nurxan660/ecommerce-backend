package com.example.ecommerce.exception;

public class NicknameAlreadyExistException extends RuntimeException{
    public NicknameAlreadyExistException(String message) {
        super(message);
    }
}
