package com.example.ecommerce.controller;


import com.example.ecommerce.dto.AddItemToCartRequest;
import com.example.ecommerce.dto.CartItemResponse;
import com.example.ecommerce.dto.CartResponseWithTotal;
import com.example.ecommerce.dto.UpdateQty;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity addItem(@RequestBody AddItemToCartRequest req){

            cartService.addItemToCart(req);
            return ResponseEntity.ok("Item successfully added to cart");
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getCart(@RequestParam Long userId){

        CartResponseWithTotal res=cartService.getCartItems(userId);
        return ResponseEntity.ok(res);
    }


    @DeleteMapping("/delete/user/{userId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteItem(@PathVariable Long userId,@PathVariable Long itemId){

            cartService.deleteItemFromCart(userId,itemId);
            return ResponseEntity.ok("Item successfully deleted");

    }


    @PutMapping("/change/qty/user/{userId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateQty(@RequestBody UpdateQty updateQty, @PathVariable Long userId, @PathVariable Long itemId){

            cartService.updateQty(updateQty,userId,itemId);
            return ResponseEntity.ok("qty successfully updated");



    }

    @GetMapping("/getTotalQty")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity getTotalQty(@RequestParam Long userId){

        Integer res=cartService.calculateTotalQty(userId);
        return ResponseEntity.ok(res);
    }
















}
