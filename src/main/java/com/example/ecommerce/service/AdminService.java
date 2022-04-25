package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.exception.UserNotFound;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;


    public List<User> getAllUsers(){
        List<User> users= (List<User>) userRepository.findAll();
        return  users;
    }
    public List<Order> getAllOrders(){
        List<Order> orders= (List<Order>) orderRepository.findAll();
        return  orders;
    }
}
