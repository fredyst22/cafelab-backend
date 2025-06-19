package com.cafemetrix.cafelab.coffees.domain.model.commands;

/**
 * Create Coffee Command
 */
public record CreateCoffeeCommand(
        String name,
        String region,
        String variety,
        Double totalWeight
) {
}