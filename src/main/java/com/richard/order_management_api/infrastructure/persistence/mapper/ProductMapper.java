package com.richard.order_management_api.infrastructure.persistence.mapper;

import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.infrastructure.persistence.entity.ProductEntity;

public class ProductMapper {

    // Entity → Domain
    public static Product toDomain(ProductEntity entity){
        return new Product(
                entity.getId(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock(),
                entity.isActive()
        );
    }

    // Domain → Entity
    public static ProductEntity toEntity(Product product){
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        entity.setStock(product.getStock());
        entity.setActive(product.isActive());
        return entity;
    }

    // Domain → DTO
    public static ProductResponse toResponse(Product product){
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }
}
