package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.infrastructure.persistence.entity.UserEntity;
import com.richard.order_management_api.infrastructure.persistence.repository.JpaUserRepository;
import com.richard.order_management_api.web.exception.UsernameExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {

    private final JpaUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(String username, String password) {
        boolean exists = userRepository.findByUsername(username).isPresent();

        if(exists) {
            throw new UsernameExistsException();
        }

        UserEntity user = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(List.of("ROLE_USER"))
                .build();

        userRepository.save(user);
    }
}
