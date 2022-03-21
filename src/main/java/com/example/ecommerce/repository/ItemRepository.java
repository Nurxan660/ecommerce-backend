package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {
    List<Item> findByIsPopular(Boolean isPopular);
    List<Item> findByCategoryId(Long id);
}
