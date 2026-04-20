package com.richard.order_management_api.web.controller;

import com.richard.order_management_api.application.dto.*;
import com.richard.order_management_api.application.usecase.LoginUseCase;
import com.richard.order_management_api.application.usecase.LogoutUseCase;
import com.richard.order_management_api.application.usecase.RegisterUseCase;
import com.richard.order_management_api.infrastructure.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final RegisterUseCase registerUseCase;
    private final LoginUseCase loginUseCase;
    private final LogoutUseCase logoutUseCase;

    public AuthController(JwtService jwtService, RegisterUseCase registerUseCase, LoginUseCase loginUseCase, LogoutUseCase logoutUseCase) {
        this.jwtService = jwtService;
        this.registerUseCase = registerUseCase;
        this.loginUseCase = loginUseCase;
        this.logoutUseCase = logoutUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request, HttpServletRequest httpRequest) {
        registerUseCase.register(request.getUsername(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        201,
                        "User registered successfully",
                        httpRequest.getRequestURI(),
                        "OK"
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletRequest httpRequest) {
        AuthResponse response = loginUseCase.execute(request);

        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "Login successful",
                        httpRequest.getRequestURI(),
                        response
                )
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshTokenRequest request, HttpServletRequest httpRequest) {
        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "Refresh successful",
                        httpRequest.getRequestURI(),
                        request.getRefreshToken()
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshTokenRequest request, HttpServletRequest httpRequest) {
        String refreshToken =  request.getRefreshToken();
        logoutUseCase.execute(refreshToken);
        return ResponseEntity.ok(
                ApiResponse.success(
                        200,
                        "Logged out successfully",
                        httpRequest.getRequestURI(),
                        null
                )
        );

    }
}
