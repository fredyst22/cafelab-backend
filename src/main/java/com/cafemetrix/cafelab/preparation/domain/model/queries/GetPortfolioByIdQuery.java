package com.cafemetrix.cafelab.preparation.domain.model.queries;

/**
 * Query for getting a portfolio by ID
 */
public record GetPortfolioByIdQuery(Long id) {
    public GetPortfolioByIdQuery {
        if (id == null || id <= 0) throw new IllegalArgumentException("Id es requerido y debe ser positivo");
    }
} 