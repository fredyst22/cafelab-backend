package com.cafemetrix.cafelab.preparation.domain.model.commands;

/**
 * Command for updating an ingredient
 */
public record UpdateIngredientCommand(
    Long ingredientId,
    String name,
    Double amount,
    String unit
) {
    public UpdateIngredientCommand {
        if (ingredientId == null || ingredientId <= 0) throw new IllegalArgumentException("IngredientId es requerido y debe ser positivo");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name es requerido");
        if (amount == null || amount <= 0) throw new IllegalArgumentException("Amount es requerido y debe ser positivo");
        if (unit == null || unit.isBlank()) throw new IllegalArgumentException("Unit es requerido");
    }
} 