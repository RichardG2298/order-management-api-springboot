package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.web.exception.InvalidProductException;
import com.richard.order_management_api.web.exception.ProductNotFoundException;

public class GetByIdProductUseCase {
    private final ProductRepository  productRepository;

    public GetByIdProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Long id) {
        if(id == null || id <= 0){
            throw new InvalidProductException("Id must be greater than 0");
        }
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
