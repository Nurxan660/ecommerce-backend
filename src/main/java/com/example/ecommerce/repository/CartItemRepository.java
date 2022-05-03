package com.example.ecommerce.repository;


import com.example.ecommerce.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItem,Long> {
    Optional<CartItem> findByCartUserIdAndItemItmId(Long id, Long itmId);
    void deleteByItemItmIdAndCartUserId(Long itmId,Long id);
    List<CartItem> findAllByCartUserIdOrderByItemItmId(Long id);
    Boolean existsByCartUserId(Long id);


}
