package com.richard.order_management_api.application.dto;

import java.math.BigDecimal;

public class ProductResponse {
    private String name;
    private BigDecimal price;

    public ProductResponse() {}

    public ProductResponse(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
