package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.web.exception.ProductsNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetAllProductUseCase {
    private final ProductRepository productRepository;
    public GetAllProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> execute(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable);
        if (productsPage.isEmpty()) {
            throw new ProductsNotFoundException();
        }
        return productsPage;
    }
}
