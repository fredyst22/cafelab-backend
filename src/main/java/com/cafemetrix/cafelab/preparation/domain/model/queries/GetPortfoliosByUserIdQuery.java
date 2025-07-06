package com.cafemetrix.cafelab.preparation.domain.model.queries;

/**
 * Query for getting portfolios by user ID
 */
public record GetPortfoliosByUserIdQuery(Long userId) {
    public GetPortfoliosByUserIdQuery {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
    }
} 