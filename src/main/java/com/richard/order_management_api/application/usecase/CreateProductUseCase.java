package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.CreateProductRequest;
import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUseCase {
    private final ProductRepository productRepository;

    public CreateProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse execute(CreateProductRequest request){
        Product product = new Product(
                null,
                request.getName(),
                request.getPrice(),
                request.getStock(),
                true
        );
        Product productSave = productRepository.save(product);

        return ProductMapper.toResponse(productSave);
    }
}
