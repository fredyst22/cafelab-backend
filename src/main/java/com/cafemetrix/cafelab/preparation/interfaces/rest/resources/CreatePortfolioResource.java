package com.cafemetrix.cafelab.preparation.interfaces.rest.resources;

/**
 * Resource for creating a portfolio
 */
public record CreatePortfolioResource(
    Long userId,
    String name
) {} 