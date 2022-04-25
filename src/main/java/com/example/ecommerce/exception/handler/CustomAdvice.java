package com.example.ecommerce.exception.handler;


import com.example.ecommerce.dto.LoginRequest;
import com.example.ecommerce.dto.ResponseMessage;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.*;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.requestBody.LoginBody;
import com.example.ecommerce.service.AuthService;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

@ControllerAdvice
public class CustomAdvice {
    @Autowired
    private LoginBody loginBody;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @ExceptionHandler({EmailAlreadyExistException.class, NicknameAlreadyExistException.class, EmailAndNicknameAlreadyExistException.class, ValidateException.class, UsernameNotFoundException.class})
    public ResponseEntity<ResponseMessage> handleRegistrationException(RuntimeException e){
        ResponseMessage exception=new ResponseMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseMessage> handleLoginException(AuthenticationException e){
        LoginRequest loginRequest= loginBody.getLoginRequest();
        User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()->new UserNotFound("email not found"));
        if(user.isAccountNonLocked()){
            if(user.getFailedAttempt()<AuthService.MAX_FAILED_ATTEMPTS){
                authService.increaseFailedAttempt(user);
                return ResponseEntity.status(401).body(new ResponseMessage("password or nickname incorrect"));
            }
            else{
                authService.lock(user);
                return ResponseEntity.status(401).body(new ResponseMessage("Your account has been blocked for "+AuthService.LOCK_TIME_DURATION_MIN+" minutes"));
            }
        }
        else{
            if(authService.unlockWhenTimeExpired(user)){
                return ResponseEntity.status(401).body(new ResponseMessage("Your account has been unlocked. Please try to login again"));
            }
            else{
                return ResponseEntity.status(401).body(new ResponseMessage("Your account blocked , please try to login later"));
            }
        }

    }

    @ExceptionHandler({TokenExpiredException.class,TokenNotFoundException.class,EmailConfirmedException.class})
    public ResponseEntity<ResponseMessage> handleRefreshTokenException(RuntimeException e){
        ResponseMessage exception=new ResponseMessage(e.getMessage());
        return ResponseEntity.status(401).body(exception);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseMessage> handleAccessDeniedExceptionException(){
        return ResponseEntity.status(403).body(new ResponseMessage("You don't have access to this resourse"));
    }


}
