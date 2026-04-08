package com.richard.order_management_api.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductUpdateRequest {
    private String name;
    private BigDecimal price;
}
