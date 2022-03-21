package com.example.ecommerce.controller;


import com.example.ecommerce.dto.UpdateUserRequest;
import com.example.ecommerce.exception.UserNotFound;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {
    @Autowired
    UserService userService;



    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity update(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest){
        try{
            userService.update(id, updateUserRequest);
            return ResponseEntity.ok("User updated");
        }
        catch (UserNotFound ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }
    }
