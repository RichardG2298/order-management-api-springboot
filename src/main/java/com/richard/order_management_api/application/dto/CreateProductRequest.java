package com.richard.order_management_api.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotNull
    private String name;

    @NotNull
    private BigDecimal price;

    @NotNull
    private int stock;

}
