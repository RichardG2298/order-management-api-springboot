package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.web.exception.ProductNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCase {

    private final ProductRepository productRepository;

    public DeleteProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void execute(Long id){
        Product product =  productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        product.delete();

        productRepository.save(product);
    }
}
