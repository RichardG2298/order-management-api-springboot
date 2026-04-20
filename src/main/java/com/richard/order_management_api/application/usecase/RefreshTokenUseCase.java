package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.AuthResponse;
import com.richard.order_management_api.infrastructure.persistence.entity.RefreshTokenEntity;
import com.richard.order_management_api.infrastructure.persistence.repository.JpaUserRepository;
import com.richard.order_management_api.infrastructure.security.JwtService;
import com.richard.order_management_api.infrastructure.security.RefreshTokenService;
import com.richard.order_management_api.web.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCase {

    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final JpaUserRepository jpaUserRepository;

    public AuthResponse execute(String refreshToken) {
        RefreshTokenEntity entity = refreshTokenService.validate(refreshToken);

        List<String> roles = jpaUserRepository.findByUsername(entity.getUsername())
                .orElseThrow(UserNotFoundException::new)
                .getRoles();

        String newAccessToken = jwtService.generateAccessToken(entity.getUsername(), roles);

        return new AuthResponse(newAccessToken, refreshToken);
    }
}
