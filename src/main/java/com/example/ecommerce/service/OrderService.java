package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrder;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserAddressRepository userAddressRepository;
    
    @Transactional
    public Order createOrder(CreateOrder createOrder, Long userId) {
        List<OrderItem> orderItemList=new ArrayList<>();
        List<CartItem> cartItem=cartItemRepository.findAllByCartUserId(userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("usr not found"));
        Order order = new Order();
        Optional<UserAddress> optUserAddress=userAddressRepository.findByUserId(userId);
        if(!optUserAddress.isPresent()) {
            UserAddress userAddress = new UserAddress();
            userAddress.setStreet(createOrder.getStreet());
            userAddress.setHomeNumber(createOrder.getHomeNumber());
            userAddress.setApartment(createOrder.getApartment());
            userAddress.setFloor(createOrder.getFloor());
            userAddress.setUser(user);
            order.setUserAddress(userAddress);
        }
        if(optUserAddress.isPresent()) {
            UserAddress userAddress = optUserAddress.get();
            order.setUserAddress(userAddress);
        }
        cartItem.stream().forEach(item->{
            OrderItem orderItem=new OrderItem();
            orderItem.setItem(item.getItem());
            orderItem.setQty(item.getQty());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        });
        order.setOrderItems(orderItemList);
        order.setTotal(calculateTotal(userId));
        order.setCreatedAt(LocalDateTime.now());
         orderRepository.save(order);
         cartRepository.deleteByUserId(userId);
         return order;
    }

    public Integer calculateTotal(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findAllByCartUserId(userId);
        Integer sum = cartItems.stream().mapToInt(cartItem -> cartItem.getItem().getPrice()*cartItem.getQty()).sum();
        return sum;

    }
}