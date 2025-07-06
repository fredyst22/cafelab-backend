package com.cafemetrix.cafelab.preparation.interfaces.rest.resources;

/**
 * Resource for representing a portfolio
 */
public record PortfolioResource(
    Long id,
    Long userId,
    String name,
    String createdAt
) {} 