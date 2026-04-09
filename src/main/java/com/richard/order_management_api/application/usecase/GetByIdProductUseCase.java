package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import com.richard.order_management_api.web.exception.InvalidProductException;
import com.richard.order_management_api.web.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetByIdProductUseCase {
    private final ProductRepository  productRepository;

    public GetByIdProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public ProductResponse execute(Long id) {
        if(id == null || id <= 0){
            throw new InvalidProductException("Id must be greater than 0");
        }

        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.validateIsActive();

        return ProductMapper.toResponse(product);
    }
}
