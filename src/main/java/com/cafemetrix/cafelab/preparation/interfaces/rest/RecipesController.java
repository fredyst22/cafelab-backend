package com.cafemetrix.cafelab.preparation.interfaces.rest;

import com.cafemetrix.cafelab.preparation.interfaces.acl.PreparationContextFacade;
import com.cafemetrix.cafelab.preparation.interfaces.rest.resources.*;
import com.cafemetrix.cafelab.preparation.interfaces.rest.transform.*;
import com.cafemetrix.cafelab.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/recipes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Recipes", description = "Recipe Management Endpoints")
public class RecipesController {
    private final PreparationContextFacade preparationContextFacade;

    public RecipesController(PreparationContextFacade preparationContextFacade) {
        this.preparationContextFacade = preparationContextFacade;
    }

    @Operation(summary = "Create a new recipe")
    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody CreateRecipeResource resource) {
        var recipeId = preparationContextFacade.createRecipe(
            resource.userId(), resource.name(), resource.imageUrl(),
            resource.extractionMethod(), resource.extractionCategory(), resource.ratio(),
            resource.cuppingSessionId(), resource.portfolioId(),
            resource.preparationTime(), resource.steps(),
            resource.tips(), resource.cupping(), resource.grindSize()
        );

        if (recipeId == 0L) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo crear la receta"));
        }

        var recipe = preparationContextFacade.getRecipeById(recipeId);
        if (recipe.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener la receta creada"));
        }

        var ingredients = preparationContextFacade.getIngredientsByRecipeId(recipeId);
        var recipeResource = RecipeResourceFromEntityAssembler.toResourceFromEntity(recipe.get(), ingredients);
        return new ResponseEntity<>(recipeResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all recipes")
    @GetMapping
    public ResponseEntity<List<RecipeResource>> getAllRecipes() {
        var recipes = preparationContextFacade.getAllRecipes();
        var recipeResources = recipes.stream()
            .map(recipe -> {
                var ingredients = preparationContextFacade.getIngredientsByRecipeId(recipe.getId());
                return RecipeResourceFromEntityAssembler.toResourceFromEntity(recipe, ingredients);
            })
            .collect(Collectors.toList());
        return ResponseEntity.ok(recipeResources);
    }

    @Operation(summary = "Get a recipe by ID")
    @GetMapping("/{recipeId}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long recipeId) {
        var recipe = preparationContextFacade.getRecipeById(recipeId);
        if (recipe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var ingredients = preparationContextFacade.getIngredientsByRecipeId(recipeId);
        var recipeResource = RecipeResourceFromEntityAssembler.toResourceFromEntity(recipe.get(), ingredients);
        return ResponseEntity.ok(recipeResource);
    }

    @Operation(summary = "Update a recipe")
    @PutMapping("/{recipeId}")
    public ResponseEntity<?> updateRecipe(@PathVariable Long recipeId, @RequestBody UpdateRecipeResource resource) {
        var updateRecipeCommand = UpdateRecipeCommandFromResourceAssembler.toCommandFromResource(recipeId, resource);
        var updatedRecipeId = preparationContextFacade.updateRecipe(
            updateRecipeCommand.recipeId(), updateRecipeCommand.name(), updateRecipeCommand.imageUrl(),
            updateRecipeCommand.extractionMethod(), updateRecipeCommand.extractionCategory(), updateRecipeCommand.ratio(),
            updateRecipeCommand.cuppingSessionId(), updateRecipeCommand.portfolioId(),
            updateRecipeCommand.preparationTime(), updateRecipeCommand.steps(),
            updateRecipeCommand.tips(), updateRecipeCommand.cupping(), updateRecipeCommand.grindSize()
        );

        if (updatedRecipeId == 0L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo actualizar la receta"));
        }

        var recipe = preparationContextFacade.getRecipeById(updatedRecipeId);
        if (recipe.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener la receta actualizada"));
        }

        var ingredients = preparationContextFacade.getIngredientsByRecipeId(updatedRecipeId);
        var recipeResource = RecipeResourceFromEntityAssembler.toResourceFromEntity(recipe.get(), ingredients);
        return ResponseEntity.ok(recipeResource);
    }

    @Operation(summary = "Delete a recipe")
    @DeleteMapping("/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long recipeId) {
        var deleted = preparationContextFacade.deleteRecipe(recipeId);
        if (deleted) {
            return ResponseEntity.ok(new MessageResource("Receta eliminada exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo eliminar la receta"));
        }
    }
} 