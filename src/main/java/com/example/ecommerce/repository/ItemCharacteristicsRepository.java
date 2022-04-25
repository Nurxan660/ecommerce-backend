package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ItemCharacteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.Column;
import java.util.List;

public interface ItemCharacteristicsRepository extends CrudRepository<ItemCharacteristics,Long> {
    List<ItemCharacteristics> findByItemCharacteristicKeyItmIdAndIndexOfSimpleCharNotOrderByIndexOfSimpleChar(Long itmId,Long indexOfSimpleChar);
    List<ItemCharacteristics> findByItemCharacteristicKeyItmIdAndIndexOfDetailCharNotOrderByIndexOfDetailChar(Long itmId,Long indexOfSimpleChar);
    // Page<ItemCharacteristics> findByItemCharacteristicKeySubCharacteristicIdInAndItemPriceBetween(Pageable pageable, List<Long> subCharacteristicsId, Integer price1, Integer price2);



}
