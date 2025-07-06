package com.cafemetrix.cafelab.preparation.domain.services;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Recipe;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetAllRecipesQuery;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetRecipeByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Recipe Query Service Interface
 */
public interface RecipeQueryService {
    List<Recipe> handle(GetAllRecipesQuery query);
    Optional<Recipe> handle(GetRecipeByIdQuery query);
} 