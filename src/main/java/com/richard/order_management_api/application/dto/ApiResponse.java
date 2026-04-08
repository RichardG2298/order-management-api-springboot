package com.richard.order_management_api.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonPropertyOrder({ "status", "message", "code", "data" })
public class ApiResponse<T> {
    private final int status;
    private final String message;
    private final String code;
    private final LocalDateTime timestamp;
    private final String path;
    private final T data;

    private ApiResponse(int status, String message, String code, T data, String path) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(status, message, null, data, null);
    }

    public static <T> ApiResponse<T> error(int status, String message, String code, String path) {
        return new ApiResponse<>(status, message, code, null, path);
    }

}
