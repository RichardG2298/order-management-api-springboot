package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public class ProductAlreadyDeletedException extends  BusinessException {
    public ProductAlreadyDeletedException(Long id) {
        super("Product already deleted with id: " + id, "PRODUCT_ALREADY_DELETED");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
