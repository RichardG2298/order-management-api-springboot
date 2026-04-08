package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public abstract class BusinessException extends RuntimeException{
    private final String code;

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public abstract HttpStatus getStatus();

    public String getCode() {
        return code;
    }
}
