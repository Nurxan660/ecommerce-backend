package com.example.ecommerce.exception;

public class EmailAndNicknameAlreadyExistException extends RuntimeException{
    public EmailAndNicknameAlreadyExistException(String message) {
        super(message);
    }
}
