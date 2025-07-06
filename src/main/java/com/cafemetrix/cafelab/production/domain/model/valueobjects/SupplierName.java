package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * SupplierName Value Object
 */
@Embeddable
public record SupplierName(String value) {
    public SupplierName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del proveedor no puede ser nulo o vacío");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("El nombre del proveedor no puede tener más de 100 caracteres");
        }
    }

    public SupplierName() {
        this(null);
    }
} 