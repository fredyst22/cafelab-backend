package com.cafemetrix.cafelab.preparation.interfaces.rest.resources;

/**
 * Resource for updating a portfolio
 */
public record UpdatePortfolioResource(
    String name
) {
    public UpdatePortfolioResource {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name es requerido");
    }
} 