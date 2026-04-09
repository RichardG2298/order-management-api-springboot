package com.richard.order_management_api.web.controller;

import com.richard.order_management_api.application.dto.*;
import com.richard.order_management_api.application.usecase.*;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.infrastructure.persistence.mapper.PageMapper;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetByIdProductUseCase getByIdProductUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final PurchaseProductUseCase purchaseProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, GetByIdProductUseCase getByIdProductUseCase, GetAllProductsUseCase getAllProductsUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, PurchaseProductUseCase purchaseProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getByIdProductUseCase = getByIdProductUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.purchaseProductUseCase = purchaseProductUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllProducts(
            ProductFilter filter,
            @PageableDefault(size = 10, page = 0) Pageable pageable,
            HttpServletRequest request
    ) {
        var page = getAllProductsUseCase.execute(filter, pageable);

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Products retrieved successfully",
                        request.getRequestURI(),
                        PageMapper.toPageResponse(page)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        var response = getByIdProductUseCase.execute(id);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Product retrieved succesfully",
                        request.getRequestURI(),
                        response
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @Valid @RequestBody CreateProductRequest productRequest,
            HttpServletRequest request
    ) {
        var response = createProductUseCase.execute(productRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                HttpStatus.CREATED.value(),
                                "Product created succesfully",
                                request.getRequestURI(),
                                response
                        )
                );
    }

    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse<ProductResponse>> purchase(
            @RequestBody PurchaseRequest purchaseRequest,
            HttpServletRequest request
    ) {
        ProductResponse response = purchaseProductUseCase.execute(purchaseRequest);

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Product purchased successfully",
                        request.getRequestURI(),
                        response
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id,
            @RequestBody UpdateProductRequest updateProductRequest,
            HttpServletRequest request
    ) {
        ProductResponse response = updateProductUseCase.execute(id, updateProductRequest);

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Product updated successfully",
                        request.getRequestURI(),
                        response
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> delete(
            @PathVariable Long id,
            HttpServletRequest  request
    ) {
        deleteProductUseCase.execute(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Product deleted successfully",
                        request.getRequestURI(),
                        null
                )
        );
    }
}
