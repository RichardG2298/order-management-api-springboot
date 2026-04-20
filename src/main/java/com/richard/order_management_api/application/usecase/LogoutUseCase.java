package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.infrastructure.security.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutUseCase {

    private final RefreshTokenService refreshTokenService;

    public void execute(String refreshToken){
        refreshTokenService.revoke(refreshToken);
    }
}
