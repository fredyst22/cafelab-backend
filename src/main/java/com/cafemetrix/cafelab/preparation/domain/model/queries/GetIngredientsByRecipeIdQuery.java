package com.cafemetrix.cafelab.preparation.domain.model.queries;

/**
 * Query for getting ingredients by recipe id
 */
public record GetIngredientsByRecipeIdQuery(Long recipeId) {
    public GetIngredientsByRecipeIdQuery {
        if (recipeId == null || recipeId <= 0) throw new IllegalArgumentException("RecipeId es requerido y debe ser positivo");
    }
} 