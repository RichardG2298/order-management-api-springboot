package com.richard.order_management_api.domain.repository;

import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);

    Optional<Product> findById(Long id);

    Page<ProductEntity> findAllWithFilters(String name, Boolean active, Pageable pageable);
}
