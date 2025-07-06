package com.cafemetrix.cafelab.preparation.domain.services;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Ingredient;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetIngredientsByRecipeIdQuery;

import java.util.List;

/**
 * Ingredient Query Service Interface
 */
public interface IngredientQueryService {
    List<Ingredient> handle(GetIngredientsByRecipeIdQuery query);
} 