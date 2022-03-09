package com.example.ecommerce.exception;

public class EmailAndNicknameAlreadyExistException extends Exception{
    public EmailAndNicknameAlreadyExistException(String message) {
        super(message);
    }
}
