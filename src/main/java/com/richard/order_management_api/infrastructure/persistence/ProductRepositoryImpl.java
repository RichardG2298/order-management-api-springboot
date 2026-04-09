package com.richard.order_management_api.infrastructure.persistence;

import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.entity.ProductEntity;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import com.richard.order_management_api.infrastructure.persistence.repository.JpaProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryImpl(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductMapper.toEntity(product);
        ProductEntity saved = jpaRepository.save(entity);
        return ProductMapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id)
                .map(ProductMapper::toDomain);
    }

    @Override
    public Page<ProductEntity> findAllWithFilters(String name, Boolean active, Pageable pageable) {
        return jpaRepository.findAllWithFilters(
                        name,
                        active,
                        pageable
                ); // 🔥 Domain → DTO
    }
}
