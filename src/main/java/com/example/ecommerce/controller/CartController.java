package com.example.ecommerce.controller;


import com.example.ecommerce.dto.AddItemToCartRequest;
import com.example.ecommerce.dto.UpdateQty;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity addItem(@RequestBody AddItemToCartRequest req){

            cartService.addItemToCart(req);
            return ResponseEntity.ok("Item successfully added to cart");


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
















}
