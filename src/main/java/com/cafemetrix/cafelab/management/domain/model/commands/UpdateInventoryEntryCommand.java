package com.cafemetrix.cafelab.management.domain.model.commands;

import java.time.LocalDateTime;

/**
 * Command for updating an inventory entry
 */
public record UpdateInventoryEntryCommand(
    Long inventoryEntryId,
    Long coffeeLotId,
    Double quantityUsed,
    LocalDateTime dateUsed,
    String finalProduct
) {
    public UpdateInventoryEntryCommand {
        if (inventoryEntryId == null || inventoryEntryId <= 0) throw new IllegalArgumentException("InventoryEntryId es requerido y debe ser positivo");
        if (coffeeLotId == null || coffeeLotId <= 0) throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");
        if (quantityUsed == null || quantityUsed <= 0) throw new IllegalArgumentException("QuantityUsed es requerido y debe ser positivo");
        if (dateUsed == null) throw new IllegalArgumentException("DateUsed es requerido");
        if (finalProduct == null || finalProduct.isBlank()) throw new IllegalArgumentException("FinalProduct es requerido");
    }
} 