package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ResponseMessage;
import com.example.ecommerce.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdminController {
    @Autowired
    private AdminService adminService;


    @GetMapping("/getAllUsers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok(adminService.getAllUsers());
    }
    @GetMapping("/getAllOrders")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity getAllOrders(){
        return ResponseEntity.ok(adminService.getAllOrders());
    }




}
