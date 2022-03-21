package com.example.ecommerce.controller;


import com.example.ecommerce.dto.FilterCharacteristicsResponse;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public ResponseEntity<List<Category>> getCategories(){
        return ResponseEntity.ok(categoryService.getCategories());
    }


    @GetMapping("/get/characteristics")
    public ResponseEntity<List<FilterCharacteristicsResponse>> getSubCharacteristics(@RequestParam Long id){
        return ResponseEntity.ok(categoryService.getAllSubCharacteristics(id));
    }









}
