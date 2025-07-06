package com.cafemetrix.cafelab.preparation.interfaces.acl;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Ingredient;
import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Portfolio;
import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Recipe;

import java.util.List;
import java.util.Optional;

/**
 * Interface for Preparation Context Facade
 */
public interface PreparationContextFacade {
    // Recipe methods
    Long createRecipe(Long userId, String name, String imageUrl, String extractionMethod,
                     String extractionCategory, String ratio, Long cuppingSessionId, Long portfolioId, Integer preparationTime,
                     String steps, String tips, String cupping, String grindSize);
    Long updateRecipe(Long recipeId, String name, String imageUrl, String extractionMethod,
                     String extractionCategory, String ratio, Long cuppingSessionId, Long portfolioId, 
                     Integer preparationTime, String steps, String tips, String cupping, String grindSize);
    boolean deleteRecipe(Long recipeId);
    List<Recipe> getAllRecipes();
    Optional<Recipe> getRecipeById(Long recipeId);

    // Ingredient methods
    Long createIngredient(Long recipeId, String name, Double amount, String unit);
    Long updateIngredient(Long ingredientId, String name, Double amount, String unit);
    boolean deleteIngredient(Long ingredientId);
    List<Ingredient> getIngredientsByRecipeId(Long recipeId);

    // Portfolio methods
    Long createPortfolio(Long userId, String name);
    Long updatePortfolio(Long portfolioId, String name);
    boolean deletePortfolio(Long portfolioId);
    List<Portfolio> getAllPortfolios();
    Optional<Portfolio> getPortfolioById(Long portfolioId);
    List<Portfolio> getPortfoliosByUserId(Long userId);
} 