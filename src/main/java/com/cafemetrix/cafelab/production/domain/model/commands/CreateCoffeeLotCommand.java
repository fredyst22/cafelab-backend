package com.cafemetrix.cafelab.production.domain.model.commands;

import java.util.List;

/**
 * Command for creating a new coffee lot
 */
public record CreateCoffeeLotCommand(
    Long userId,
    Long supplierId,
    String lotName,
    String coffeeType,
    String processingMethod,
    Integer altitude,
    Double weight,
    String origin,
    String status,
    List<String> certifications
) {
    public CreateCoffeeLotCommand {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
        if (supplierId == null || supplierId <= 0) throw new IllegalArgumentException("SupplierId es requerido y debe ser positivo");
        if (lotName == null || lotName.isBlank()) throw new IllegalArgumentException("LotName es requerido");
        if (coffeeType == null || coffeeType.isBlank()) throw new IllegalArgumentException("CoffeeType es requerido");
        if (processingMethod == null || processingMethod.isBlank()) throw new IllegalArgumentException("ProcessingMethod es requerido");
        if (altitude == null || altitude <= 0) throw new IllegalArgumentException("Altitude es requerido y debe ser positivo");
        if (weight == null || weight <= 0) throw new IllegalArgumentException("Weight es requerido y debe ser positivo");
        if (origin == null || origin.isBlank()) throw new IllegalArgumentException("Origin es requerido");
        if (status == null || status.isBlank()) throw new IllegalArgumentException("Status es requerido");
    }
} 