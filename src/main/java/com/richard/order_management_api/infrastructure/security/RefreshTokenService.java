package com.richard.order_management_api.infrastructure.security;

import com.richard.order_management_api.infrastructure.persistence.entity.RefreshTokenEntity;
import com.richard.order_management_api.infrastructure.persistence.repository.JpaRefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final JpaRefreshTokenRepository repository;

    public RefreshTokenEntity save(String token, String username, LocalDateTime expiredAt) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        refreshToken.setToken(token);
        refreshToken.setUsername(username);
        refreshToken.setExpiration(expiredAt);
        refreshToken.setRevoked(false);

        return repository.save(refreshToken);
    }

    public RefreshTokenEntity validate(String token) {
        RefreshTokenEntity entity = repository.findByTokenAndRevokedFalse(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if(entity.getExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid refresh token");
        }

        return entity;
    }

    public void revoke(String token) {
        RefreshTokenEntity entity = repository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        entity.setRevoked(true);
        repository.save(entity);
    }

    public void revokeAllByUsername(String username) {
        repository.revokeAllByUsername(username);
    }
}
