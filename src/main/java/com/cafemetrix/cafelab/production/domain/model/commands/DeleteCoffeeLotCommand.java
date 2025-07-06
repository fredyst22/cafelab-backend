package com.cafemetrix.cafelab.production.domain.model.commands;

/**
 * Command for deleting a coffee lot
 */
public record DeleteCoffeeLotCommand(Long coffeeLotId) {
    public DeleteCoffeeLotCommand {
        if (coffeeLotId == null || coffeeLotId <= 0) throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");
    }
} 