package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Origin Value Object
 */
@Embeddable
public record Origin(String value) {
    public Origin {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El origen del café no puede ser nulo o vacío");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("El origen del café no puede tener más de 100 caracteres");
        }
    }

    public Origin() {
        this(null);
    }
} 