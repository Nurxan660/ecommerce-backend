package com.example.ecommerce.service;

import com.example.ecommerce.dto.ItemsByCategoryResponse;
import com.example.ecommerce.dto.PopularItemsResponse;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<PopularItemsResponse> getPopularItems(){
        List<Item> item=itemRepository.findByIsPopular(true);
        List<PopularItemsResponse> popularItemsResponses=item.stream()
                .map(d->modelMapper.map(d,PopularItemsResponse.class))
                .collect(Collectors.toList());
        return popularItemsResponses;
    }

    public List<ItemsByCategoryResponse> getItemsByCategory(Long id){
        List<Item> items=itemRepository.findByCategoryId(id);
        List<ItemsByCategoryResponse> response=items.stream().map(d->modelMapper.map(d,ItemsByCategoryResponse.class))
                .collect(Collectors.toList());
        return response;
    }


}
