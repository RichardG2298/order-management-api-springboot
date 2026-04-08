package com.richard.order_management_api.web.exception;

import org.springframework.http.HttpStatus;

public class ProductsNotFoundException extends BusinessException{
    public ProductsNotFoundException() {
        super("No products found", "PRODUCTS_NOT_FOUND");
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
