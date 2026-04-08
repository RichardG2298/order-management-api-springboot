package com.richard.order_management_api.application.dto;

import lombok.Getter;

@Getter
public class PurchaseRequest {
    private Long productId;
    private int quantity;
}
