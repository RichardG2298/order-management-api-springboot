package com.richard.order_management_api.infrastructure.persistence.repository;

import com.richard.order_management_api.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
