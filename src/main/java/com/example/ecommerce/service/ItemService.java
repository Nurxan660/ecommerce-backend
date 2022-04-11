package com.example.ecommerce.service;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.Characteristics;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.ItemCharacteristics;
import com.example.ecommerce.repository.ItemCharacteristicsRepository;
import com.example.ecommerce.repository.ItemRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemCharacteristicsRepository itemCharacteristicsRepository;

    public List<PopularItemsResponse> getPopularItems(){

        List<Item> item=itemRepository.findByIsPopular(true);
        List<PopularItemsResponse> popularItemsResponses=item.stream()
                .map(d->modelMapper.map(d,PopularItemsResponse.class))
                .collect(Collectors.toList());
        return popularItemsResponses;
    }

    public ItemsByCategoryResponse getItemsByCategory(Long id,int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        Page<Item> items=itemRepository.findByCategoryId(pageable,id);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ItemsByCategoryResponse res=modelMapper.map(items,ItemsByCategoryResponse.class);
        return res;
    }

    public SimpleItemCharacteristicsResponse getItemsWithSimpleProperties(Long id){
        SimpleItemCharacteristicsResponse response=new SimpleItemCharacteristicsResponse();
        List<SubTitleAndValueResponse> subTitleAndValueResponses=new ArrayList<>();
        List<ItemCharacteristics> properties=itemCharacteristicsRepository.findByItemCharacteristicKeyItmIdAndItemCharacteristicKeyIndexOfSimpleCharNotOrderByItemCharacteristicKeyIndexOfSimpleChar(id, 0L);
        if(properties.size()!=0) {
            response.setItmId(properties.get(0).getItem().getItmId());
            response.setName(properties.get(0).getItem().getName());
            response.setDescription(properties.get(0).getItem().getDescription());
            response.setPrice(properties.get(0).getItem().getPrice());
            response.setImages(properties.get(0).getItem().getImages());
            properties.forEach(d -> {
                subTitleAndValueResponses.add(new SubTitleAndValueResponse(d.getSubCharacteristics().getId(),d.getSubCharacteristics().getSubTitle(), d.getSubCharacteristics().getValue()));
            });
            response.setSubTitleAndValue(subTitleAndValueResponses);
        }
        return response;
    }

    public List<CharacteristicsResponse> getItemsWithDetailProperties(Long id){
        List<CharacteristicsResponse> response=new ArrayList<>();
        List<ItemCharacteristics> properties=itemCharacteristicsRepository.findByItemCharacteristicKeyItmIdAndItemCharacteristicKeyIndexOfDetailCharNotOrderByItemCharacteristicKeyIndexOfDetailChar(id, 0L);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Map<Characteristics,List<ItemCharacteristics>> grouped=properties.stream().collect(Collectors.groupingBy(d->d.getSubCharacteristics().getCharacteristics()));
        grouped.forEach((k,v)->{
            List<SubTitleAndValueResponse> subTitleAndValueResponses=v.stream().map(d->modelMapper.map(d,SubTitleAndValueResponse.class)).collect(Collectors.toList());
            CharacteristicsResponse characteristicsResponse=new CharacteristicsResponse(k.getPropertiesId(),k.getPropertiesName(),subTitleAndValueResponses);
            response.add(characteristicsResponse);

        });
        return response;

    }



}
