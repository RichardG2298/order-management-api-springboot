package com.richard.order_management_api.application.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;

    public ProductResponse() {}

    public ProductResponse(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
