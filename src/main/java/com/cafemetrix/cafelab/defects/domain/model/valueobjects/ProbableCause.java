package com.cafemetrix.cafelab.defects.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * ProbableCause Value Object
 */
@Embeddable
public record ProbableCause(String value) {
    public ProbableCause() {
        this(null);
    }

    public ProbableCause {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La causa probable no puede ser nula o vacía");
        }
    }
}