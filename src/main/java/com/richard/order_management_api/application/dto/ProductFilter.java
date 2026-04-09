package com.richard.order_management_api.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilter {
    private String name;
    private Boolean active;
}
