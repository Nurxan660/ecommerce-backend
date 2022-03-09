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
public class CartController {
    @Autowired
    private CartService cartService;


    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity addItem(@RequestBody AddItemToCartRequest req){
        try {
            cartService.addItemToCart(req);
            return ResponseEntity.ok("Item successfully added to cart");

        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }


    @DeleteMapping("/delete/user/{userId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity deleteItem(@PathVariable Long userId,@PathVariable Long itemId){
        try {
            cartService.deleteItemFromCart(userId,itemId);
            return ResponseEntity.ok("Item successfully deleted");

        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }


    @PutMapping("/change/qty/user/{userId}/items/{itemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity updateQty(@RequestBody UpdateQty updateQty, @PathVariable Long userId, @PathVariable Long itemId){
        try {
            cartService.updateQty(updateQty,userId,itemId);
            return ResponseEntity.ok("qty successfully updated");

        }
        catch(Exception e){

            return ResponseEntity.badRequest().body(e.getMessage());

        }


    }
















}
