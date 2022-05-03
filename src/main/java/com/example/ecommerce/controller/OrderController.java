package com.example.ecommerce.controller;


import com.example.ecommerce.dto.CreateOrder;
import com.example.ecommerce.dto.OrderResponse;
import com.example.ecommerce.entity.Order;
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

            Order order=orderService.createOrder(createOrder,userId);
            return ResponseEntity.ok(new OrderResponse("Order successfully created",order));
    }

    @PostMapping("/createEmailOrder/{email}")
    @PreAuthorize("hasRole('USER')")
    public void createEmailOrder(@PathVariable String email,@RequestBody Order order){

        orderService.createEmailOrder(email,order);
    }

    @GetMapping("/get/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getOrder(@PathVariable Long userId){
        return ResponseEntity.ok(orderService.getOrders(userId));
    }













}
