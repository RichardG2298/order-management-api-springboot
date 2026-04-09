package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.ProductFilter;
import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductsUseCase {

    private final ProductRepository productRepository;

    public GetAllProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductResponse> execute(ProductFilter filter, Pageable pageable) {

        return productRepository.findAllWithFilters(
                        filter.getName(),
                        filter.getActive(),
                        pageable
                )
                .map(ProductMapper::toDomain)   // 🔥 Entity → Domain
                .map(ProductMapper::toResponse); // 🔥 Domain → DTO
    }
}
