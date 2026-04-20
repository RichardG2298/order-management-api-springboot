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

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final JpaUserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;

    public AuthResponse execute(AuthRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(UserNotFoundException::new);

        refreshTokenService.revokeAllByUsername(request.getUsername());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = jwtService.generateAccessToken(user.getUsername(), user.getRoles());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        return new AuthResponse(accessToken, refreshToken);
    }
}
