package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * CoffeeLotName Value Object
 */
@Embeddable
public record CoffeeLotName(String value) {
    public CoffeeLotName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del lote de café no puede ser nulo o vacío");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("El nombre del lote de café no puede tener más de 100 caracteres");
        }
    }

    public CoffeeLotName() {
        this(null);
    }
} 