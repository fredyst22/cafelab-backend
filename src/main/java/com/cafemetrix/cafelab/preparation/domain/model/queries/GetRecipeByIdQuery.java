package com.cafemetrix.cafelab.preparation.domain.model.queries;

/**
 * Query for getting a recipe by id
 */
public record GetRecipeByIdQuery(Long id) {
    public GetRecipeByIdQuery {
        if (id == null || id <= 0) throw new IllegalArgumentException("Id es requerido y debe ser positivo");
    }
} 