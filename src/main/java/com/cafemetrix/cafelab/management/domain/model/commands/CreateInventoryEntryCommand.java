package com.cafemetrix.cafelab.management.domain.model.commands;

import java.time.LocalDateTime;

/**
 * Command for creating a new inventory entry
 */
public record CreateInventoryEntryCommand(
    Long userId,
    Long coffeeLotId,
    Double quantityUsed,
    LocalDateTime dateUsed,
    String finalProduct
) {
    public CreateInventoryEntryCommand {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
        if (coffeeLotId == null || coffeeLotId <= 0) throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");
        if (quantityUsed == null || quantityUsed <= 0) throw new IllegalArgumentException("QuantityUsed es requerido y debe ser positivo");
        if (dateUsed == null) throw new IllegalArgumentException("DateUsed es requerido");
        if (finalProduct == null || finalProduct.isBlank()) throw new IllegalArgumentException("FinalProduct es requerido");
    }
} 