package com.richard.order_management_api.infrastructure.persistence.repository;

import com.richard.order_management_api.data.ProductTestData;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.infrastructure.persistence.entity.ProductEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class JpaProductRepositoryTest {

    @Autowired
    private JpaProductRepository repository;

    @Test
    void shouldFilterByName() {
        ProductEntity product = new ProductEntity();
        product.setName("Laptop");
        product.setPrice(BigDecimal.TEN);
        product.setStock(10);
        product.setActive(true);

        repository.save(product);

        Page<ProductEntity> result = repository.findAllWithFilters("lap", true, PageRequest.of(0, 10));

        assertFalse(result.getContent().isEmpty());

    }
}