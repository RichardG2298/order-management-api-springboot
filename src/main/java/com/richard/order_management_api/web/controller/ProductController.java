package com.richard.order_management_api.web.controller;

import com.richard.order_management_api.application.dto.*;
import com.richard.order_management_api.application.usecase.*;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetByIdProductUseCase getByIdProductUseCase;
    private final GetAllProductUseCase getAllProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, GetByIdProductUseCase getByIdProductUseCase, GetAllProductUseCase getAllProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getByIdProductUseCase = getByIdProductUseCase;
        this.getAllProductUseCase = getAllProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productsPage = getAllProductUseCase.execute(pageable);

        // mapear a DTO
        List<ProductResponse> items = productsPage.getContent()
                .stream()
                .map(ProductMapper::toResponse)
                .toList();

        PageResponse<ProductResponse> pageResponse = new PageResponse<>(
                items,
                productsPage.getNumber() + 1,
                productsPage.getSize(),
                (int) productsPage.getTotalElements(),
                productsPage.getTotalPages()
        );

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Products retrieved successfully",
                        pageResponse
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id) {
        var response = ProductMapper.toResponse(getByIdProductUseCase.execute(id));
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Product retrieved succesfully",
                        response
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@RequestBody CreateProductRequest request) {
        var response = ProductMapper.toResponse(createProductUseCase.execute(request));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                HttpStatus.CREATED.value(),
                                "Product created succesfully",
                                response
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id, @RequestBody ProductUpdateRequest request) {
        Product updatedProduct = updateProductUseCase.execute(id, request);
        ProductResponse response = ProductMapper.toResponse(updatedProduct);

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Product updated successfully",
                        response
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> delete(@PathVariable Long id) {
        deleteProductUseCase.execute(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Product deleted successfully",
                        null
                )
        );
    }
}
