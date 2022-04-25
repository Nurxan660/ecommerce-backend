package com.example.ecommerce.controller;

import com.example.ecommerce.dto.*;
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
        List<ItemsResponse> itemsRespons =itemService.getPopularItems();
        return ResponseEntity.ok(itemsRespons);
    }

    @GetMapping("/get/itemsByCategory")
    public ResponseEntity getItems(@RequestParam Long id,@RequestParam int page,@RequestParam int size){
        ItemsByCategoryResponse responses=itemService.getItemsByCategory(id,page,size);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/get/itemsWithSimpleProperties")
    public ResponseEntity getItemsWithSimpleProperties(@RequestParam Long id){
        SimpleItemCharacteristicsResponse response=itemService.getItemsWithSimpleProperties(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get/itemsWithDetailProperties")
    public ResponseEntity getItemsWithDetailProperties(@RequestParam Long id){
        List<CharacteristicsResponse> response=itemService.getItemsWithDetailProperties(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get/itemsByFilter")
    public ResponseEntity getItemsByFilter(@RequestBody ItemsByFilterRequest ids,@RequestParam int page,@RequestParam int size,@RequestParam Integer minPrice,@RequestParam Integer maxPrice ){
        ItemsByFilterResponse response=itemService.getItemsByFilter(ids,page,size,minPrice,maxPrice);
        return ResponseEntity.ok(response);
    }


















}
