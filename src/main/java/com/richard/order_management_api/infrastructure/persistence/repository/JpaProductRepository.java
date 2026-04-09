package com.richard.order_management_api.infrastructure.persistence.repository;

import com.richard.order_management_api.infrastructure.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    @Query(
            value = """
        SELECT p FROM ProductEntity p
        WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:active IS NULL OR p.active = :active)
    """,
            countQuery = """
        SELECT COUNT(p) FROM ProductEntity p
        WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
          AND (:active IS NULL OR p.active = :active)
    """
    )
    Page<ProductEntity> findAllWithFilters(
            @Param("name") String name,
            @Param("active") Boolean active,
            Pageable pageable
    );
}
