package com.richard.order_management_api.infrastructure.persistence.mapper;

import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.infrastructure.persistence.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static Product toDomain(ProductEntity entity){
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock()
        );
    }

    public static ProductEntity toEntity(Product product){
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
        return entity;
    }

    public static ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getName(),
                product.getPrice()
        );
    }

    public static List<ProductResponse> toResponse(List<Product> products){
        return products.stream().map(ProductMapper::toResponse).collect(Collectors.toList());
    }
}
