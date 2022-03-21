package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ItemsByCategoryResponse;
import com.example.ecommerce.dto.PopularItemsResponse;
import com.example.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ItemController {

    @Autowired
    private ItemService itemService;


    @GetMapping("/getPopularItems")
    public ResponseEntity getPopularItems(){
        List<PopularItemsResponse> popularItemsResponses=itemService.getPopularItems();
        return ResponseEntity.ok(popularItemsResponses);
    }

    @GetMapping("/get/itemsByCategory")
    public ResponseEntity getPopularItems(@RequestParam Long id){
        List<ItemsByCategoryResponse> responses=itemService.getItemsByCategory(id);
        return ResponseEntity.ok(responses);
    }
















}
