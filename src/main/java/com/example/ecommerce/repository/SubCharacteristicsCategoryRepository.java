package com.example.ecommerce.repository;

import com.example.ecommerce.compositeKey.CharacteristicsCategoryKey;
import com.example.ecommerce.entity.SubCharacteristicsCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubCharacteristicsCategoryRepository extends CrudRepository<SubCharacteristicsCategory, CharacteristicsCategoryKey> {
    List<SubCharacteristicsCategory> findByCategoryId(Long id);
}
