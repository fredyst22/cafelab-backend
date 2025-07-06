package com.cafemetrix.cafelab.preparation.application.internal.queryservices;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Ingredient;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetIngredientsByRecipeIdQuery;
import com.cafemetrix.cafelab.preparation.domain.services.IngredientQueryService;
import com.cafemetrix.cafelab.preparation.infrastructure.persistence.jpa.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientQueryServiceImpl implements IngredientQueryService {
    private final IngredientRepository ingredientRepository;

    public IngredientQueryServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public List<Ingredient> handle(GetIngredientsByRecipeIdQuery query) {
        return ingredientRepository.findByRecipeId(query.recipeId());
    }
} 