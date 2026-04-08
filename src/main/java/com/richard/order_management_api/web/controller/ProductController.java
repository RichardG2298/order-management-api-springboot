package com.richard.order_management_api.web.controller;

import com.richard.order_management_api.application.dto.*;
import com.richard.order_management_api.application.usecase.*;
import com.richard.order_management_api.domain.model.Product;
import com.richard.order_management_api.infrastructure.persistence.mapper.ProductMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
    private final PurchaseProductUseCase purchaseProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, GetByIdProductUseCase getByIdProductUseCase, GetAllProductUseCase getAllProductUseCase, UpdateProductUseCase updateProductUseCase, DeleteProductUseCase deleteProductUseCase, PurchaseProductUseCase purchaseProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.getByIdProductUseCase = getByIdProductUseCase;
        this.getAllProductUseCase = getAllProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.purchaseProductUseCase = purchaseProductUseCase;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            HttpServletRequest request
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
                        request.getRequestURI(),
                        pageResponse
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(
            @PathVariable Long id,
            HttpServletRequest request
    ) {
        var response = ProductMapper.toResponse(getByIdProductUseCase.execute(id));
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
        var response = ProductMapper.toResponse(createProductUseCase.execute(productRequest));
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
        Product product = purchaseProductUseCase.execute(purchaseRequest);
        ProductResponse response = ProductMapper.toResponse(product);

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
        Product updatedProduct = updateProductUseCase.execute(id, updateProductRequest);
        ProductResponse response = ProductMapper.toResponse(updatedProduct);

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
