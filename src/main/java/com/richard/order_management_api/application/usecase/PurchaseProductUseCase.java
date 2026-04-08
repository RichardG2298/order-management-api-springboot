package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.PurchaseRequest;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.web.exception.InactiveProductException;
import com.richard.order_management_api.web.exception.ProductAlreadyDeletedException;
import com.richard.order_management_api.web.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseProductUseCase {
    private final ProductRepository productRepository;

    public Product execute(PurchaseRequest  purchaseRequest) {
        Product product = productRepository.findById(purchaseRequest.getProductId())
                .orElseThrow(() -> new ProductNotFoundException(purchaseRequest.getProductId()));

        product.decreaseStock(purchaseRequest.getQuantity());
        return productRepository.save(product);


    }
}
