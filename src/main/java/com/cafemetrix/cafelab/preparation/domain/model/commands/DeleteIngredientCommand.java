package com.cafemetrix.cafelab.preparation.domain.model.commands;

/**
 * Command for deleting an ingredient
 */
public record DeleteIngredientCommand(
    Long ingredientId
) {
    public DeleteIngredientCommand {
        if (ingredientId == null || ingredientId <= 0) throw new IllegalArgumentException("IngredientId es requerido y debe ser positivo");
    }
} 