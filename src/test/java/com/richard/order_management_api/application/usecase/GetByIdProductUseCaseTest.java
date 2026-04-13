package com.richard.order_management_api.application.usecase;

import com.richard.order_management_api.application.dto.ProductResponse;
import com.richard.order_management_api.data.ProductTestData;
import com.richard.order_management_api.domain.repository.ProductRepository;
import com.richard.order_management_api.web.exception.ProductNotFoundException;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetByIdProductUseCaseTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private GetByIdProductUseCase useCase;

    @Test
    void shouldReturnProductWhenExists() {
        //When
        when(repository.findById(1L)).thenReturn(Optional.of(ProductTestData.Product()));

        //Then
        ProductResponse result = useCase.execute(1L);

        //Assert
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Laptop", result.getName());
        Assertions.assertEquals(BigDecimal.TEN, result.getPrice());
    }

    @Test
    void shouldThrownWhenProductNotFound() {
        //When
        when(repository.findById(1L)).thenReturn(Optional.empty());

        //Then
        assertThrows(ProductNotFoundException.class, () -> {
            useCase.execute(1L);
        });
    }
}