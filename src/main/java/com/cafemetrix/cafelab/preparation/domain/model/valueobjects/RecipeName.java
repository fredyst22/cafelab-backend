package com.cafemetrix.cafelab.preparation.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * RecipeName Value Object
 */
@Embeddable
public record RecipeName(String value) {
    public RecipeName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre de la receta no puede ser nulo o vacío");
        }
        if (value.length() > 20) {
            throw new IllegalArgumentException("El nombre de la receta no puede tener más de 20 caracteres");
        }
    }

    public RecipeName() {
        this(null);
    }
} 