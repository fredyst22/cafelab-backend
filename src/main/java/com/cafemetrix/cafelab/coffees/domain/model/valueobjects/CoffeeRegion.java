package com.cafemetrix.cafelab.coffees.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * CoffeeRegion Value Object
 */
@Embeddable
public record CoffeeRegion(String value) {
    public CoffeeRegion() {
        this(null);
    }

    public CoffeeRegion {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La región del café no puede ser nula o vacía");
        }
    }
}