package com.richard.order_management_api.data;

import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.infrastructure.persistence.entity.ProductEntity;

import java.math.BigDecimal;

public class ProductTestData {

    public static Product Product() {
        return new Product(1L, "Laptop", BigDecimal.TEN, 10, true);
    }

    public static ProductResponse ProductResponse() {return new ProductResponse(1L, "Laptop", BigDecimal.TEN);}


}
