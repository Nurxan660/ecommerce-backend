package com.example.ecommerce.requestBody;

import com.example.ecommerce.dto.LoginRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
public class LoginBody {
    private LoginRequest loginRequest;

    public LoginRequest getLoginRequest() {
        return loginRequest;
    }

    public void setLoginRequest(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
    }
}
