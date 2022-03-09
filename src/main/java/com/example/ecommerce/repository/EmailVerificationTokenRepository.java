package com.example.ecommerce.repository;

import com.example.ecommerce.entity.EmailVerificationToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmailVerificationTokenRepository extends CrudRepository<EmailVerificationToken,Long> {

    Optional<EmailVerificationToken> findByToken(String token);
}
