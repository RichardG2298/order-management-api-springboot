package com.richard.order_management_api.domain.model;

import com.richard.order_management_api.web.exception.InactiveProductException;
import com.richard.order_management_api.web.exception.InsufficientStockException;
import com.richard.order_management_api.web.exception.ProductAlreadyDeletedException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.BitSet;

@Getter
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private int stock;
    private boolean active;

    public Product(Long id, String name, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.active = true;
    }

    public void decreaseStock(int quantity) {
        validateIsActive();
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if(this.stock < quantity) {
            throw new InsufficientStockException("Insufficient stock for product: " + name);
        }
        this.stock -= quantity;
    }

    public void increaseStock(int quantity) {
        validateIsActive();
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        this.stock += quantity;
    }

    public void update(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public void delete() {
        if(!this.active){
            throw new ProductAlreadyDeletedException(id);
        }
        this.active = false;
    }

    public void validateIsActive() {
        if (!this.active) {
            throw new InactiveProductException(this.id);
        }
    }

    public void deactivate() {
        validateIsActive();
        this.active = false;
    }
}
