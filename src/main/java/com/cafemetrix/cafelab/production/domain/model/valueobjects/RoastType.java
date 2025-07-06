package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * RoastType Value Object
 */
@Embeddable
public record RoastType(String value) {
    public RoastType {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El tipo de tueste no puede ser nulo o vacío");
        }
        
        String normalizedValue = value.trim();
        if (!isValidRoastType(normalizedValue)) {
            throw new IllegalArgumentException("Tipo de tueste no válido. Debe ser: Ligero, Medio, Medio-Oscuro, Oscuro");
        }
    }

    public RoastType() {
        this(null);
    }

    private static boolean isValidRoastType(String value) {
        return value.equals("Ligero") || 
               value.equals("Medio") || 
               value.equals("Medio-Oscuro") || 
               value.equals("Oscuro");
    }
} 