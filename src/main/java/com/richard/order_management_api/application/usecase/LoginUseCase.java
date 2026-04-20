package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.AuthRequest;
import com.richard.order_management_api.application.dto.AuthResponse;
import com.richard.order_management_api.infrastructure.persistence.repository.JpaUserRepository;
import com.richard.order_management_api.infrastructure.security.JwtService;
import com.richard.order_management_api.infrastructure.security.RefreshTokenService;
import com.richard.order_management_api.web.exception.InvalidCredentialsException;
import com.richard.order_management_api.web.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final JpaUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public AuthResponse execute(AuthRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        refreshTokenService.revokeAllByUsername(request.getUsername());

        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRoles());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        refreshTokenService.save(refreshToken, user.getUsername(), LocalDateTime.now());

        return new AuthResponse(accessToken, refreshToken);
    }
}
