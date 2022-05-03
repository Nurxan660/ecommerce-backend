package com.example.ecommerce.controller;


import com.example.ecommerce.dto.ChangeAddressRequest;
import com.example.ecommerce.dto.ResponseMessage;
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

            userService.update(id, updateUserRequest);
            return ResponseEntity.ok(new ResponseMessage("successfully updated"));
    }

    @GetMapping("/get/address/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity update(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserAddress(id));
    }


    @PutMapping("/change/address/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ChangeAddressRequest req){
        userService.changeUserAddress(req,id);
        return ResponseEntity.ok(new ResponseMessage("Address successfully has changed"));
    }
}
