package com.richard.order_management_api.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@Getter
@JsonPropertyOrder({ "status", "message", "code", "data" })
public class ApiResponse<T> {
    private final int status;
    private final String message;
    private final String code;
    private final T data;

    //Error
    public ApiResponse(int status, String message, String code, T data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    //Success
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.code = null;
    }

}
