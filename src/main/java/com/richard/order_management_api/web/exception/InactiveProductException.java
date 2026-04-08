package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public class InactiveProductException extends BusinessException {
    public InactiveProductException(Long id) {
        super("Product is inactive with id: " + id, "INACTIVE_PRODUCT");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
