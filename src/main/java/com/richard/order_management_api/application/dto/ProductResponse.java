package com.richard.order_management_api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
}
