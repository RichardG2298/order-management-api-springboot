package com.richard.order_management_api.application.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}
