package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BusinessException{
    public ProductNotFoundException(Long id) {
        super("Product not found with id: " + id, "PRODUCT_NOT_FOUND");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
