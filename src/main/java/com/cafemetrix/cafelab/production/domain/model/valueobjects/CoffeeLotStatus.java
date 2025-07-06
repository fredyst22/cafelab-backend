package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.Arrays;
import java.util.List;

/**
 * CoffeeLotStatus Value Object
 */
@Embeddable
public record CoffeeLotStatus(String value) {
    private static final List<String> VALID_STATUSES = Arrays.asList("green", "roasted");
    
    public CoffeeLotStatus {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El estado del lote no puede ser nulo o vacío");
        }
        if (value.length() > 20) {
            throw new IllegalArgumentException("El estado del lote no puede tener más de 20 caracteres");
        }
        if (!VALID_STATUSES.contains(value)) {
            throw new IllegalArgumentException("El estado del lote debe ser uno de: " + VALID_STATUSES);
        }
    }

    public CoffeeLotStatus() {
        this(null);
    }
} 