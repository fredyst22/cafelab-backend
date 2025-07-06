package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * SupplierLocation Value Object
 */
@Embeddable
public record SupplierLocation(String value) {
    public SupplierLocation {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("La ubicación del proveedor no puede ser nula o vacía");
        }
        if (value.length() > 200) {
            throw new IllegalArgumentException("La ubicación del proveedor no puede tener más de 200 caracteres");
        }
    }

    public SupplierLocation() {
        this(null);
    }
} 