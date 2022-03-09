package com.example.ecommerce.validation;


import com.example.ecommerce.exception.ValidateException;

public class Validation {
    public static boolean validateEmail(String email) throws ValidateException {
        boolean mail=email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

        if(mail){
            return true;
        }
            throw new ValidateException("Email doesn't match the pattern");

    }
    public static boolean validatePassword(String password) throws ValidateException {
        boolean pass=password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

        if(pass){
            return true;
        }
        throw new ValidateException("Password doesn't match the pattern");

    }

    public static boolean validateNickname(String nickname) throws ValidateException {
        boolean nick=nickname.matches("^(?=\\S+$).{4,20}$");

        if(nick){
            return true;
        }
        throw new ValidateException("Nickname doesn't match the pattern");
    }



}
