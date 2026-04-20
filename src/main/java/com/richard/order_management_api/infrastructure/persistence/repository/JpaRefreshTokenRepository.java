package com.richard.order_management_api.infrastructure.persistence.repository;

import com.richard.order_management_api.infrastructure.persistence.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String refreshToken);
    Optional<RefreshTokenEntity> findByTokenAndRevokedFalse(String username);

    @Modifying
    @Query("UPDATE RefreshTokenEntity t SET t.revoked = true WHERE t.username = :username AND t.revoked = false")
    void revokeAllByUsername(String username);
}
