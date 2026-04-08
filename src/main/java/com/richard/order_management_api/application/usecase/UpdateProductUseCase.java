package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.ProductUpdateRequest;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.web.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUseCase {
    private final ProductRepository productRepository;

    public UpdateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Long id, ProductUpdateRequest request) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existingProduct.update(request.getName(), request.getPrice());
        return productRepository.save(existingProduct);
    }
}
