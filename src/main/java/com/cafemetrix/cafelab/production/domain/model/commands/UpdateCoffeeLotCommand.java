package com.cafemetrix.cafelab.production.domain.model.commands;

import java.util.List;

/**
 * Command for updating a coffee lot
 */
public record UpdateCoffeeLotCommand(
    Long coffeeLotId,
    String lotName,
    String coffeeType,
    String processingMethod,
    Integer altitude,
    Double weight,
    String origin,
    String status,
    List<String> certifications
) {
    public UpdateCoffeeLotCommand {
        if (coffeeLotId == null || coffeeLotId <= 0) throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");
        if (lotName == null || lotName.isBlank()) throw new IllegalArgumentException("LotName es requerido");
        if (coffeeType == null || coffeeType.isBlank()) throw new IllegalArgumentException("CoffeeType es requerido");
        if (processingMethod == null || processingMethod.isBlank()) throw new IllegalArgumentException("ProcessingMethod es requerido");
        if (altitude == null || altitude <= 0) throw new IllegalArgumentException("Altitude es requerido y debe ser positivo");
        if (weight == null || weight <= 0) throw new IllegalArgumentException("Weight es requerido y debe ser positivo");
        if (origin == null || origin.isBlank()) throw new IllegalArgumentException("Origin es requerido");
        if (status == null || status.isBlank()) throw new IllegalArgumentException("Status es requerido");
    }
} 