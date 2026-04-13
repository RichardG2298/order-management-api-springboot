package com.richard.order_management_api.web.controller;

import com.richard.order_management_api.application.dto.AuthRequest;
import com.richard.order_management_api.application.dto.AuthResponse;
import com.richard.order_management_api.infrastructure.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest auth) {
        if("admin".equals(auth.getUsername()) && "12345".equals(auth.getPassword())) {
            String token = jwtService.generateJwtToken(auth.getUsername());
            return ResponseEntity.ok(new AuthResponse(token));
        }

        return ResponseEntity.status(401).body("Invalid username or password");
    }

    @PostMapping("/adim/test")
    public ResponseEntity<?> test(@RequestBody AuthRequest auth) {
        return ResponseEntity.status(401).body("Admin access granted");
    }
}
