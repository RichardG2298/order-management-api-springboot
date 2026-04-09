package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.application.dto.PurchaseRequest;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import com.richard.order_management_api.web.exception.InactiveProductException;
import com.richard.order_management_api.web.exception.ProductAlreadyDeletedException;
import com.richard.order_management_api.web.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PurchaseProductUseCase {
    private final ProductRepository productRepository;

    @Transactional
    public ProductResponse execute(PurchaseRequest  purchaseRequest) {
        Product product = productRepository.findById(purchaseRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(purchaseRequest.getProductId()));

        product.validateIsActive();

        product.decreaseStock(purchaseRequest.getQuantity());
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toResponse(updatedProduct);


    }
}
