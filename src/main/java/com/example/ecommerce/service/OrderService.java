package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateOrder;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.entity.enums.OrderStatus;
import com.example.ecommerce.entity.enums.PaymentType;
import com.example.ecommerce.exception.CartItemException;
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
    @Autowired
    private MailService mailService;
    
    @Transactional
    public Order createOrder(CreateOrder createOrder, Long userId) {
        List<OrderItem> orderItemList=new ArrayList<>();
        List<CartItem> cartItem=cartItemRepository.findAllByCartUserIdOrderByItemItmId(userId);
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
        if(cartItem.size()==0){
            throw new CartItemException("cart cannot be empty");
        }
        cartItem.stream().forEach(item->{
            OrderItem orderItem=new OrderItem();
            orderItem.setItem(item.getItem());
            orderItem.setQty(item.getQty());
            orderItem.setOrder(order);
            orderItemList.add(orderItem);
        });
        order.setOrderItems(orderItemList);
        order.setTotal(createOrder.getTotal());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.IN_PROCESSING);
        if (createOrder.getPaymentType().equals("cash to courier")) {
            order.setPaymentType(PaymentType.CASH_TO_COURIER);
        } else {
            order.setPaymentType(PaymentType.CARD_TO_COURIER);
        }
        orderRepository.save(order);
         cartRepository.deleteByUserId(userId);
         return order;
    }



    public void createEmailOrder(String email,Order order){
        final String[] orderDetail = {"Your order:\n"};
        order.getOrderItems().forEach((d)->{
            orderDetail[0]+=d.getItem().getName()+" "+"Количество: "+d.getQty()+","+"\n";
        });

        orderDetail[0]+="Total: "+order.getTotal()+"\n"+
                "Address: "+order.getUserAddress().getStreet()+" "+order.getUserAddress().getHomeNumber()
                +" "+"apartment: "+order.getUserAddress().getApartment()+" "+"floor: "+order.getUserAddress().getFloor()+"\n"+
                "Thanks for order!\n" +
                "You can see the status of your order in your personal account";
        mailService.send(email,orderDetail[0],"Your order");
    }

    public List<Order> getOrders(Long id){
        List<Order> orders =orderRepository.findByUserAddressUserId(id);
        return orders;
    }
}