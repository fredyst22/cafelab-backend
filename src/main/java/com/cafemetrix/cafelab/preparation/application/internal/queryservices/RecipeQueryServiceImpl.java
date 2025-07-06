package com.cafemetrix.cafelab.preparation.application.internal.queryservices;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Recipe;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetAllRecipesQuery;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetRecipeByIdQuery;
import com.cafemetrix.cafelab.preparation.domain.services.RecipeQueryService;
import com.cafemetrix.cafelab.preparation.infrastructure.persistence.jpa.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeQueryServiceImpl implements RecipeQueryService {
    private final RecipeRepository recipeRepository;

    public RecipeQueryServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> handle(GetAllRecipesQuery query) {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> handle(GetRecipeByIdQuery query) {
        return recipeRepository.findById(query.id());
    }
} 