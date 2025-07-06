package com.cafemetrix.cafelab.preparation.interfaces.rest.resources;

/**
 * Resource for representing an ingredient
 */
public record IngredientResource(
    Long id,
    Long recipeId,
    String name,
    Double amount,
    String unit
) {} 