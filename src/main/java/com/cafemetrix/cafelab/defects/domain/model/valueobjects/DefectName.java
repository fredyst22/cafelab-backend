package com.cafemetrix.cafelab.defects.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * DefectName Value Object
 */
@Embeddable
public record DefectName(String value) {
    public DefectName() {
        this(null);
    }

    public DefectName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del defecto no puede ser nulo o vacío");
        }
    }
}