package com.richard.order_management_api.application.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponse<T> {
    private List<T> items;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;

    public PageResponse(List<T> items, int pageNumber, int pageSize, int totalElements, int totalPages) {
        this.items = items;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }
}
