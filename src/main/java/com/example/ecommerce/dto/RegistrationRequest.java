package com.example.ecommerce.dto;

import java.util.Set;

public class RegistrationRequest {
    private String nickname;
    private String email;
    private String password;
    private Set<String> role;


    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRole() {
        return role;
    }
}
