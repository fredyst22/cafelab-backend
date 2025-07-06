package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.Arrays;
import java.util.List;

/**
 * ProcessingMethod Value Object
 */
@Embeddable
public record ProcessingMethod(String value) {
    private static final List<String> VALID_METHODS = Arrays.asList("Anaeróbico", "Lavado", "Natural", "Honey");
    
    public ProcessingMethod {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El método de procesamiento no puede ser nulo o vacío");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("El método de procesamiento no puede tener más de 50 caracteres");
        }
        if (!VALID_METHODS.contains(value)) {
            throw new IllegalArgumentException("El método de procesamiento debe ser uno de: " + VALID_METHODS);
        }
    }

    public ProcessingMethod() {
        this(null);
    }
} 