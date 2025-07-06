package com.cafemetrix.cafelab.preparation.domain.services;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Recipe;
import com.cafemetrix.cafelab.preparation.domain.model.commands.CreateRecipeCommand;
import com.cafemetrix.cafelab.preparation.domain.model.commands.UpdateRecipeCommand;
import com.cafemetrix.cafelab.preparation.domain.model.commands.DeleteRecipeCommand;

import java.util.Optional;

/**
 * Recipe Command Service Interface
 */
public interface RecipeCommandService {
    Optional<Recipe> handle(CreateRecipeCommand command);
    Optional<Recipe> handle(UpdateRecipeCommand command);
    boolean handle(DeleteRecipeCommand command);
} 