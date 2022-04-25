package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.ItemCharacteristics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByIsPopular(Boolean isPopular);
    Page<Item> findByCategoryId(Pageable pageable,Long id);
    @Query(value=" WITH items as (select itm_id as id,  array_agg(sub_characteristic_id)  \n" +
            "              as arr from item_characteristics group by itm_id) \n" +
            "              select items.id as itm_id,\n" +
            "                           list_of_items.item_description as item_description ,\n" +
            "              list_of_items.item_name , \n" +
            "              list_of_items.item_price ,\n" +
            "              list_of_items.category_id, \n" +
            "                list_of_items.is_popular \n" +
            "              from items inner join list_of_items on list_of_items.itm_id=items.id\n" +
            "                           where arr @>cast(string_to_array(:ids,',') as bigint[])\n" +
            "              and list_of_items.item_price between :price1 and :price2",nativeQuery = true)
    Page<Item> findByFilterData(@Param("ids") String ids, @Param("price1") Integer price1, @Param("price2") Integer price2, Pageable pageable);
}
