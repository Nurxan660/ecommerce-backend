package com.example.ecommerce.service;

import com.example.ecommerce.dto.FilterCharacteristicsResponse;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.SubCharacteristicsCategory;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.SubCharacteristicsCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SubCharacteristicsCategoryRepository subCharacteristicsCategoryRepository;

    public List<Category> getCategories(){
        List<Category> categoryList= (List<Category>) categoryRepository.findAll();
        return categoryList;
    }

    public List<FilterCharacteristicsResponse> getAllSubCharacteristics(Long id){
        List<FilterCharacteristicsResponse> response=new ArrayList<>();
        List<SubCharacteristicsCategory> subCharacteristics=subCharacteristicsCategoryRepository.findByCategoryId(id);
        Map<String,List<SubCharacteristicsCategory>> grouped =subCharacteristics.stream().collect(Collectors.groupingBy(d->d.getSubCharacteristics().getSubName()));
        grouped.forEach((k,v)->{
            FilterCharacteristicsResponse temp=new FilterCharacteristicsResponse(k,v);
            response.add(temp);
        });
        return response;
    }



}
