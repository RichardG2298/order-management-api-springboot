package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public class InvalidProductException extends BusinessException{
    public InvalidProductException(String message) {
        super(message, "INVALID_PRODUCT");
    }


    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
