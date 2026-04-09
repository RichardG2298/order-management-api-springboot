package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.CreateProductRequest;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(CreateProductRequest request){
        Product product = new Product(
                null,
                request.getName(),
                request.getPrice(),
                request.getStock(),
                true
        );
        return productRepository.save(product);
    }
}
