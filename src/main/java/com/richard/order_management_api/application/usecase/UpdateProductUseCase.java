package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.application.dto.UpdateProductRequest;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import com.richard.order_management_api.web.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse execute(Long id, UpdateProductRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.validateIsActive();

        existingProduct.update(request.getName(), request.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);

        return ProductMapper.toResponse(updatedProduct);
    }
}
