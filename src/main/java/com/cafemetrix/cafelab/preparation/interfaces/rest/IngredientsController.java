package com.cafemetrix.cafelab.preparation.interfaces.rest;

import com.cafemetrix.cafelab.preparation.interfaces.acl.PreparationContextFacade;
import com.cafemetrix.cafelab.preparation.interfaces.rest.resources.*;
import com.cafemetrix.cafelab.preparation.interfaces.rest.transform.UpdateIngredientCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/recipes/{recipeId}/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Recipes", description = "Recipe Ingredients Management Endpoints")
public class IngredientsController {
    private final PreparationContextFacade preparationContextFacade;

    public IngredientsController(PreparationContextFacade preparationContextFacade) {
        this.preparationContextFacade = preparationContextFacade;
    }

    @Operation(summary = "Add an ingredient to a recipe")
    @PostMapping
    public ResponseEntity<?> addIngredientToRecipe(
            @PathVariable Long recipeId,
            @RequestBody CreateIngredientResource resource) {
        
        if (!recipeId.equals(resource.recipeId())) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("El ID de la receta no coincide con el de la ruta"));
        }

        var ingredientId = preparationContextFacade.createIngredient(
            resource.recipeId(), resource.name(),
            resource.amount(), resource.unit()
        );

        if (ingredientId == 0L) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo crear el ingrediente"));
        }

        var ingredients = preparationContextFacade.getIngredientsByRecipeId(recipeId);
        var ingredient = ingredients.stream()
            .filter(i -> i.getId().equals(ingredientId))
            .findFirst();

        if (ingredient.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener el ingrediente creado"));
        }

        var ingredientResource = new IngredientResource(
            ingredient.get().getId(),
            ingredient.get().getRecipeId(),
            ingredient.get().getName(),
            ingredient.get().getAmount(),
            ingredient.get().getUnit()
        );

        return new ResponseEntity<>(ingredientResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all ingredients of a recipe")
    @GetMapping
    public ResponseEntity<List<IngredientResource>> getIngredientsByRecipeId(@PathVariable Long recipeId) {
        var ingredients = preparationContextFacade.getIngredientsByRecipeId(recipeId);
        var ingredientResources = ingredients.stream()
            .map(ingredient -> new IngredientResource(
                ingredient.getId(),
                ingredient.getRecipeId(),
                ingredient.getName(),
                ingredient.getAmount(),
                ingredient.getUnit()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(ingredientResources);
    }

    @Operation(summary = "Update an ingredient")
    @PutMapping("/{ingredientId}")
    public ResponseEntity<?> updateIngredient(
            @PathVariable Long recipeId,
            @PathVariable Long ingredientId,
            @RequestBody UpdateIngredientResource resource) {
        
        var updateIngredientCommand = UpdateIngredientCommandFromResourceAssembler.toCommandFromResource(ingredientId, resource);
        var updatedIngredientId = preparationContextFacade.updateIngredient(
            updateIngredientCommand.ingredientId(),
            updateIngredientCommand.name(),
            updateIngredientCommand.amount(),
            updateIngredientCommand.unit()
        );

        if (updatedIngredientId == 0L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo actualizar el ingrediente"));
        }

        var ingredients = preparationContextFacade.getIngredientsByRecipeId(recipeId);
        var ingredient = ingredients.stream()
            .filter(i -> i.getId().equals(updatedIngredientId))
            .findFirst();

        if (ingredient.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource("No se pudo obtener el ingrediente actualizado"));
        }

        var ingredientResource = new IngredientResource(
            ingredient.get().getId(),
            ingredient.get().getRecipeId(),
            ingredient.get().getName(),
            ingredient.get().getAmount(),
            ingredient.get().getUnit()
        );

        return ResponseEntity.ok(ingredientResource);
    }

    @Operation(summary = "Delete an ingredient")
    @DeleteMapping("/{ingredientId}")
    public ResponseEntity<?> deleteIngredient(
            @PathVariable Long recipeId,
            @PathVariable Long ingredientId) {
        
        var deleted = preparationContextFacade.deleteIngredient(ingredientId);
        if (deleted) {
            return ResponseEntity.ok(new MessageResource("Ingrediente eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo eliminar el ingrediente"));
        }
    }
} 