package com.cafemetrix.cafelab.production.domain.model.queries;

/**
 * Query for getting a coffee lot by id
 */
public record GetCoffeeLotByIdQuery(Long coffeeLotId) {
    public GetCoffeeLotByIdQuery {
        if (coffeeLotId == null || coffeeLotId <= 0) throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");
    }
} 