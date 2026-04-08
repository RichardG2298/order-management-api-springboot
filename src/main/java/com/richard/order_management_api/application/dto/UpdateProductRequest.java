package com.richard.order_management_api.application.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UpdateProductRequest {
    @Size(min = 2, message = "Name must have at least 2 characters")
    private String name;

    @Positive(message = "Price must be greater than zero")
    private BigDecimal price;
}
