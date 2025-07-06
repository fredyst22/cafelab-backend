package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * RoastProfileName Value Object
 */
@Embeddable
public record RoastProfileName(String value) {
    public RoastProfileName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del perfil de tueste no puede ser nulo o vacío");
        }
        if (value.length() > 50) {
            throw new IllegalArgumentException("El nombre del perfil de tueste no puede tener más de 50 caracteres");
        }
    }

    public RoastProfileName() {
        this(null);
    }
} 