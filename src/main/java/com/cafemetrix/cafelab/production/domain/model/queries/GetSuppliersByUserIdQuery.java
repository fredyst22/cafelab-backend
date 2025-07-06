package com.cafemetrix.cafelab.production.domain.model.queries;

/**
 * Query for getting suppliers by user id
 */
public record GetSuppliersByUserIdQuery(Long userId) {
    public GetSuppliersByUserIdQuery {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
    }
} 