package com.example.ecommerce.controller;


import com.example.ecommerce.dto.*;
import com.example.ecommerce.exception.*;
import com.example.ecommerce.requestBody.LoginBody;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.EmailVerificationTokenService;
import com.example.ecommerce.service.RefreshTokenService;
import com.example.ecommerce.validation.Validation;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    EmailVerificationTokenService emailVerificationTokenService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private LoginBody loginBody;




    @PostMapping("/signup")
    public ResponseEntity signUp( @RequestBody RegistrationRequest req){

            Validation.validateData(req.getEmail(),req.getPassword(),req.getNickname());
                authService.registration(req);
                return ResponseEntity.ok(new ResponseMessage("User registered successfully"));

    }

    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody LoginRequest loginRequest){
            loginBody.setLoginRequest(loginRequest);
            LoginResponse loginResponse = authService.signIn(loginRequest);
            return ResponseEntity.ok(loginResponse);

    }
    @DeleteMapping(value = "/logout")
    public ResponseEntity logout(@RequestParam Long userId){

            authService.logout(userId);
            return ResponseEntity.ok("Successfully logout");
    }



    @PostMapping(value = "/refreshToken")
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){

            TokenRefreshResponse tokenRefreshResponse=refreshTokenService.checkExpiration(refreshTokenRequest.getRefreshToken());
            return ResponseEntity.ok(tokenRefreshResponse);

    }




    @PostMapping("/restorePassword")
    public ResponseEntity restorePassword(@RequestBody RestorePasswordRequest restorePasswordRequest){

            authService.restorePassword(restorePasswordRequest.getEmail());
            return ResponseEntity.ok("A link has been sent to your email");


    }

    @GetMapping("/confirmToken")
    public ResponseEntity confirmToken(@RequestParam String token){

            emailVerificationTokenService.confirmTokenAndChangePassword(token);
            return ResponseEntity.ok("New password sended to your email");

    }









}
