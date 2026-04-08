package com.richard.order_management_api.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"status", "message", "code", "timestamp", "path", "data", "errors"})
public class ApiResponse<T> {
    private final int status;
    private final String message;
    private final String code;
    private final LocalDateTime timestamp;
    private final String path;
    private final T data;
    private final Object errors;

    private ApiResponse(int status, String message, String code, String path, T data, Object errors) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
        this.errors = errors;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> success(int status, String message, String path, T data) {
        return new ApiResponse<>(status, message, null, path, data, null);
    }

    public static <T> ApiResponse<T> error(int status, String message, String code, String path) {
        return new ApiResponse<>(status, message, code, path, null, null);
    }

    public static <T> ApiResponse<T> error(int status, String message, String code, String path, Object errors) {
        return new ApiResponse<>(status, message, code, path, null, errors);
    }

}
