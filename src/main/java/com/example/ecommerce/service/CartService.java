package com.example.ecommerce.service;

import com.example.ecommerce.dto.AddItemToCartRequest;
import com.example.ecommerce.dto.CartItemResponse;
import com.example.ecommerce.dto.CartResponseWithTotal;
import com.example.ecommerce.dto.UpdateQty;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.ItemRepository;
import com.example.ecommerce.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderService orderService;


    public Cart addItemToCart(AddItemToCartRequest req){
        List<CartItem> cartItems=new ArrayList<>();
        Item item=itemRepository.findById(req.getItemId()).orElseThrow(()->new RuntimeException("item not found"));
        User user= userRepository.findById(req.getUserId()).orElseThrow(()->new RuntimeException("usr not found"));
        Optional<CartItem> optCart =cartItemRepository.findByCartUserIdAndItemItmId(req.getUserId(), req.getItemId());
        if(optCart.isPresent()){
            Cart existCart=cartRepository.findByUserId(req.getUserId()).orElseThrow(()->new RuntimeException("cart not found"));
           CartItem cartItem =optCart.get();
           cartItem.setQty(cartItem.getQty()+req.getQuantity());
           cartItems.add(cartItem);
           existCart.setCartItems(cartItems);
           return cartRepository.save(existCart);
        }

        if(!cartRepository.existsByUserId(req.getUserId())){
            Cart cart =new Cart();
            cart.setUser(user);
            cartItems.add(new CartItem(req.getQuantity(),cart,item));
            cart.setCartItems(cartItems);
            return cartRepository.save(cart);
        }
        Cart existCart=cartRepository.findByUserId(req.getUserId()).orElseThrow(()->new RuntimeException("cart not found"));
       cartItems.add(new CartItem(req.getQuantity(),existCart,item));
       existCart.setCartItems(cartItems);
        return cartRepository.save(existCart);

    }

    public CartResponseWithTotal getCartItems(Long userId){
        CartResponseWithTotal cartResponseWithTotal=new CartResponseWithTotal();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        List<CartItem> cartItem=cartItemRepository.findAllByCartUserIdOrderByItemItmId(userId);
        List<CartItemResponse> res=cartItem.stream().map(d->
            modelMapper.map(d,CartItemResponse.class)
        ).collect(Collectors.toList());
        Integer sum = cartItem.stream().mapToInt(cart -> cart.getItem().getPrice()*cart.getQty()).sum();
        cartResponseWithTotal.setCartItemResponseList(res);
        cartResponseWithTotal.setTotal(sum);
        return  cartResponseWithTotal;
    }
    @Transactional
    public void deleteItemFromCart(Long userId,Long itemId){
        Item item=itemRepository.findById(itemId).orElseThrow(()->new RuntimeException("item not found"));
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("usr not found"));
        cartItemRepository.deleteByItemItmIdAndCartUserId(itemId,userId);
    }

    public CartItem updateQty(UpdateQty updateQty,Long userId,Long itemId){

       CartItem cartItem= cartItemRepository.findByCartUserIdAndItemItmId(userId,itemId).orElseThrow(()->new RuntimeException("cart item not found"));
       cartItem.setQty(updateQty.getQty());
       return cartItemRepository.save(cartItem);

    }

    public Integer calculateTotalQty(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findAllByCartUserIdOrderByItemItmId(userId);
        Integer sum = cartItems.stream().mapToInt(cartItem -> cartItem.getQty()).sum();
        return sum;
    }











}
