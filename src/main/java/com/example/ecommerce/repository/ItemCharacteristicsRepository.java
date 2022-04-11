package com.example.ecommerce.repository;

import com.example.ecommerce.entity.ItemCharacteristics;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Column;
import java.util.List;

public interface ItemCharacteristicsRepository extends CrudRepository<ItemCharacteristics,Long> {
    List<ItemCharacteristics> findByItemCharacteristicKeyItmIdAndItemCharacteristicKeyIndexOfSimpleCharNotOrderByItemCharacteristicKeyIndexOfSimpleChar(Long itmId,Long indexOfSimpleChar);
    List<ItemCharacteristics> findByItemCharacteristicKeyItmIdAndItemCharacteristicKeyIndexOfDetailCharNotOrderByItemCharacteristicKeyIndexOfDetailChar(Long itmId,Long indexOfSimpleChar);


}
