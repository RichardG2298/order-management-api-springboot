package com.richard.order_management_api.infrastructure.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageableUtils {
    private static final List<String> ALLOWED_SORTS = List.of("id", "name", "price");

    public static Pageable validate(Pageable pageable) {
        Sort validSort = Sort.by(
                pageable.getSort().stream()
                        .filter(order -> ALLOWED_SORTS.contains(order.getProperty()))
                        .toList()
        );

        return PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                validSort
        );
    }

}
