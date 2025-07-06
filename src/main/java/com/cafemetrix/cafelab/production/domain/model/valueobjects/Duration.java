package com.cafemetrix.cafelab.production.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

/**
 * Duration Value Object
 */
@Embeddable
public record Duration(Integer value) {
    public Duration {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException("La duración debe ser un valor positivo");
        }
        if (value > 1440) { // Máximo 24 horas en minutos
            throw new IllegalArgumentException("La duración no puede exceder 1440 minutos (24 horas)");
        }
    }

    public Duration() {
        this(null);
    }
} 