package com.example.ecommerce.repository;

import com.example.ecommerce.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken,Long> {
   Optional<RefreshToken> findByToken(String token);
   void deleteByUserId(Long id);
};
