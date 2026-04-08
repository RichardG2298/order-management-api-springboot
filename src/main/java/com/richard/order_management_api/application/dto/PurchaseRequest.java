package com.richard.order_management_api.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class PurchaseRequest {
    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @Positive(message = "Quantity must be greater than zero")
    private int quantity;
}
