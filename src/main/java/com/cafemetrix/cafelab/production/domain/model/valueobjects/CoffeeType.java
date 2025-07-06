package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.Arrays;
import java.util.List;

/**
 * CoffeeType Value Object
 */
@Embeddable
public record CoffeeType(String value) {
    private static final List<String> VALID_TYPES = Arrays.asList("Arábica", "Robusta", "Mezcla");
    
    public CoffeeType {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El tipo de café no puede ser nulo o vacío");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("El tipo de café no puede tener más de 50 caracteres");
        }
        if (!VALID_TYPES.contains(value)) {
            throw new IllegalArgumentException("El tipo de café debe ser uno de: " + VALID_TYPES);
        }
    }

    public CoffeeType() {
        this(null);
    }
} 