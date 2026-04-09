package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.ProductFilter;
import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import com.richard.order_management_api.infrastructure.util.PageableUtils;
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
        pageable = PageableUtils.validate(pageable);
        boolean active = filter.getActive() != null ? filter.getActive() : true; // Default to active products if not specified
        return productRepository.findAllWithFilters(
                        filter.getName(),
                        active,
                        pageable
                )
                .map(ProductMapper::toDomain)   // 🔥 Entity → Domain
                .map(ProductMapper::toResponse); // 🔥 Domain → DTO
    }
}
