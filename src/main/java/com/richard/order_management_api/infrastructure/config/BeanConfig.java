package com.richard.order_management_api.infrastructure.config;

import com.richard.order_management_api.application.usecase.CreateProductUseCase;
import com.richard.order_management_api.application.usecase.GetAllProductUseCase;
import com.richard.order_management_api.application.usecase.GetByIdProductUseCase;
import com.richard.order_management_api.domain.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    private final ProductRepository productRepository;

    public BeanConfig(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Bean
    public CreateProductUseCase createProductUseCase() {
        return new CreateProductUseCase(productRepository);
    }

    @Bean
    public GetByIdProductUseCase getByIdProductUseCase() {
        return new GetByIdProductUseCase(productRepository);
    }

    @Bean
    public GetAllProductUseCase getAllProductUseCase() {
        return new GetAllProductUseCase(productRepository);
    }
}
