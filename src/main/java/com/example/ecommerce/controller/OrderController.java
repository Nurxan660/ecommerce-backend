package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CreateOrder;
import com.example.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {

    @Autowired
    private OrderService orderService;
    @PostMapping("/create/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity createOrder(@RequestBody CreateOrder createOrder, @PathVariable Long userId){

            orderService.createOrder(createOrder,userId);
            return ResponseEntity.ok("Order successfully created");



    }













}
