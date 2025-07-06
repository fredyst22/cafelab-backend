package com.cafemetrix.cafelab.preparation.domain.model.commands;

/**
 * Command for deleting a recipe
 */
public record DeleteRecipeCommand(
    Long recipeId
) {
    public DeleteRecipeCommand {
        if (recipeId == null || recipeId <= 0) throw new IllegalArgumentException("RecipeId es requerido y debe ser positivo");
    }
} 