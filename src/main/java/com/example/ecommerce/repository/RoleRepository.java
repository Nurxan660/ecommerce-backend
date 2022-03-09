package com.example.ecommerce.repository;


import com.example.ecommerce.entity.ERole;
import com.example.ecommerce.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {
    Optional<Role> findByRole(ERole role);
}
