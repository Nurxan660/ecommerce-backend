package com.example.ecommerce.repository;

import com.example.ecommerce.entity.UserAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAddressRepository extends CrudRepository<UserAddress,Long> {

    Optional<UserAddress> findByUserId(Long id);



}
