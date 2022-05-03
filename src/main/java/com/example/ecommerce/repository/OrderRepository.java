package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends CrudRepository<Order,Long> {
   List<Order> findByUserAddressUserId(Long id);
}
