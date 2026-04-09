package com.richard.order_management_api.infrastructure.persistence.mapper;

import com.richard.order_management_api.application.dto.PageResponse;
import org.springframework.data.domain.Page;

public class PageMapper {

    public static <T> PageResponse<T> toPageResponse(Page<T> page) {
        return new PageResponse<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            (int) page.getTotalElements(),
            page.getTotalPages()
        );
    }
}
