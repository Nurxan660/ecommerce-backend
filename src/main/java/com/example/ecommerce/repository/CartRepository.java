package com.example.ecommerce.repository;


import com.example.ecommerce.entity.Cart;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartRepository extends CrudRepository<Cart,Long> {
    Boolean existsByUserId(Long id);
    Optional<Cart> findByUserId(Long id);
    void deleteByUserId(Long id);
}
