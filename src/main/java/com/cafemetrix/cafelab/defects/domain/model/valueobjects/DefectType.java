package com.cafemetrix.cafelab.defects.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * DefectType Value Object
 */
@Embeddable
public record DefectType(String value) {
    public DefectType() {
        this(null);
    }

    public DefectType {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El tipo de defecto no puede ser nulo o vacío");
        }
    }
}