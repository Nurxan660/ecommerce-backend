package com.example.ecommerce.controller;


import com.example.ecommerce.dto.*;
import com.example.ecommerce.exception.*;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.EmailVerificationTokenService;
import com.example.ecommerce.service.RefreshTokenService;
import com.example.ecommerce.validation.Validation;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    EmailVerificationTokenService emailVerificationTokenService;
    @Autowired
    private RefreshTokenService refreshTokenService;



    @PostMapping("/signup")
    public ResponseEntity signUp( @RequestBody RegistrationRequest req){
        try{
            if(
            Validation.validateEmail(req.getEmail())&&Validation.validatePassword(req.getPassword())&&Validation.validateNickname(req.getNickname())) {
                authService.registration(req);
                return ResponseEntity.ok("User registered successfully");
            }
            return null;
        }
       catch (EmailAlreadyExistException e){
           return ResponseEntity.badRequest().body(e.getMessage());
       }
        catch (NicknameAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (EmailAndNicknameAlreadyExistException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ValidateException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/signin")
    public ResponseEntity signIn(@RequestBody LoginRequest loginRequest){
        try {
            LoginResponse loginResponse = authService.signIn(loginRequest);
            return ResponseEntity.ok(loginResponse);

        }
        catch(Exception e){
            return ResponseEntity.badRequest().body("nickname or password is incorrect");

        }
    }
    @DeleteMapping(value = "/logout")
    public ResponseEntity logout(@RequestParam Long userId){

            authService.logout(userId);
            return ResponseEntity.ok("Successfully logout");
    }



    @PostMapping(value = "/refreshToken")
    public ResponseEntity refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        try {
            TokenRefreshResponse tokenRefreshResponse=refreshTokenService.checkExpiration(refreshTokenRequest.getRefreshToken());
            return ResponseEntity.ok(tokenRefreshResponse);

        }
        catch(TokenExpiredException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
        catch(TokenNotFoundException e){
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }




    @PostMapping("/restorePassword")
    public ResponseEntity restorePassword(@RequestBody RestorePasswordRequest restorePasswordRequest){
        try {
            authService.restorePassword(restorePasswordRequest.getEmail());
            return ResponseEntity.ok("A link has been sent to your email");
        }
        catch (UsernameNotFoundException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/confirmToken")
    public ResponseEntity confirmToken(@RequestParam String token){
        try{
            emailVerificationTokenService.confirmTokenAndChangePassword(token);
            return ResponseEntity.ok("New password sended to your email");

        } catch (TokenExpiredException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        } catch (EmailConfirmedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }









}
