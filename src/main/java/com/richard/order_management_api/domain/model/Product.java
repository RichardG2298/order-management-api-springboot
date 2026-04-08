package com.richard.order_management_api.domain.model;

import java.math.BigDecimal;
import java.util.BitSet;

public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private int stock;

    public Product(Long id, String name, BigDecimal price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void decreaseStock(int stock) {
        if(stock <= 0){
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if(this.stock < stock) {
            throw new RuntimeException("Insufficient stock for product: " + name);
        }
        this.stock -= stock;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }
}
