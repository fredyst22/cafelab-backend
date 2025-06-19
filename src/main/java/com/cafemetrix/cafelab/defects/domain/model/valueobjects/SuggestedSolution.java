package com.cafemetrix.cafelab.defects.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * SuggestedSolution Value Object
 */
@Embeddable
public record SuggestedSolution(String value) {
    public SuggestedSolution() {
        this(null);
    }

    public SuggestedSolution {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La solución sugerida no puede ser nula o vacía");
        }
    }
}