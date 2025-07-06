package com.cafemetrix.cafelab.preparation.domain.model.aggregates;

import com.cafemetrix.cafelab.preparation.domain.model.commands.CreateIngredientCommand;
import com.cafemetrix.cafelab.preparation.domain.model.commands.UpdateIngredientCommand;
import com.cafemetrix.cafelab.preparation.domain.model.valueobjects.IngredientName;
import com.cafemetrix.cafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * Ingredient Aggregate Root
 */
@Entity
public class Ingredient extends AuditableAbstractAggregateRoot<Ingredient> {

    @Getter
    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", length = 100))
    private IngredientName name;

    @Column(nullable = false)
    private Double amount;

    @Column(length = 10, nullable = false)
    private String unit;

    /**
     * Constructor por defecto
     */
    public Ingredient() {}

    /**
     * Constructor principal
     */
    public Ingredient(Long recipeId, String name, Double amount, String unit) {
        this.recipeId = recipeId;
        this.name = new IngredientName(name);
        this.amount = amount;
        this.unit = unit;
    }

    /**
     * Constructor con comando
     */
    public Ingredient(CreateIngredientCommand command) {
        this.recipeId = command.recipeId();
        this.name = new IngredientName(command.name());
        this.amount = command.amount();
        this.unit = command.unit();
    }

    /**
     * Método para actualizar el ingrediente
     */
    public Ingredient update(UpdateIngredientCommand command) {
        this.name = new IngredientName(command.name());
        this.amount = command.amount();
        this.unit = command.unit();
        return this;
    }

    // Getters
    public String getName() { return name.value(); }
    public Double getAmount() { return amount; }
    public String getUnit() { return unit; }
} 