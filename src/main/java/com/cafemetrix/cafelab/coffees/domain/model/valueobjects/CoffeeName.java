package com.cafemetrix.cafelab.coffees.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * CoffeeName Value Object
 */
@Embeddable
public record CoffeeName(String value) {
    public CoffeeName() {
        this(null);
    }

    public CoffeeName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del café no puede ser nulo o vacío");
        }
    }
}