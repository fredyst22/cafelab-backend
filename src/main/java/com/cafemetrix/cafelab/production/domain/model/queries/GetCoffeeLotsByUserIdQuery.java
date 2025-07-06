package com.cafemetrix.cafelab.production.domain.model.queries;

/**
 * Query for getting coffee lots by user id
 */
public record GetCoffeeLotsByUserIdQuery(Long userId) {
    public GetCoffeeLotsByUserIdQuery {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
    }
} 