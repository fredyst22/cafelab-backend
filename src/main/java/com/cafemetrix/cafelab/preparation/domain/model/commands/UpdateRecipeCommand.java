package com.cafemetrix.cafelab.preparation.domain.model.commands;

/**
 * Command for updating a recipe
 */
public record UpdateRecipeCommand(
    Long recipeId,
    String name,
    String imageUrl,
    String extractionMethod,
    String extractionCategory,
    String ratio,
    Long cuppingSessionId,
    Long portfolioId,
    Integer preparationTime,
    String steps,
    String tips,
    String cupping,
    String grindSize
) {
    public UpdateRecipeCommand {
        if (recipeId == null || recipeId <= 0) throw new IllegalArgumentException("RecipeId es requerido y debe ser positivo");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name es requerido");
        if (imageUrl == null || imageUrl.isBlank()) throw new IllegalArgumentException("ImageUrl es requerido");
        if (extractionMethod == null || extractionMethod.isBlank()) throw new IllegalArgumentException("ExtractionMethod es requerido");
        if (extractionCategory == null || extractionCategory.isBlank()) throw new IllegalArgumentException("ExtractionCategory es requerido");
        if (ratio == null || ratio.isBlank()) throw new IllegalArgumentException("Ratio es requerido");
        if (preparationTime == null || preparationTime <= 0) throw new IllegalArgumentException("PreparationTime es requerido y debe ser positivo");
        if (steps == null || steps.isBlank()) throw new IllegalArgumentException("Steps es requerido");
        if (cupping == null || cupping.isBlank()) throw new IllegalArgumentException("Cupping es requerido");
        if (grindSize == null || grindSize.isBlank()) throw new IllegalArgumentException("GrindSize es requerido");
    }
} 