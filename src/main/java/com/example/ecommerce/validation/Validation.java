package com.example.ecommerce.validation;


import com.example.ecommerce.exception.ValidateException;

public class Validation {
    public static void validateData(String email,String password,String nickname) throws ValidateException {
        boolean mail=email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        boolean pass=password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        boolean nick=nickname.matches("^(?=\\S+$).{4,20}$");


        if(!mail){
            throw new ValidateException("Email doesn't match the pattern");
        }
        if(!pass){
            throw new ValidateException("Password doesn't match the pattern");
        }
        if(!nick){
            throw new ValidateException("Nickname doesn't match the pattern");
        }
    }




}
