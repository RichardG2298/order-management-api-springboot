package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public class InsufficientStockException extends BusinessException {
    public InsufficientStockException(String message) {
        super(message, "INSUFFICIENT_STOCK");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
