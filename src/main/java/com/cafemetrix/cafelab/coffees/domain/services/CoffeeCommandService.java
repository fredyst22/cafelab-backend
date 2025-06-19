package com.cafemetrix.cafelab.coffees.domain.services;

import com.cafemetrix.cafelab.coffees.domain.model.aggregates.Coffee;
import com.cafemetrix.cafelab.coffees.domain.model.commands.CreateCoffeeCommand;

import java.util.Optional;

/**
 * Coffee Command Service
 */
public interface CoffeeCommandService {
    /**
     * Handle Create Coffee Command
     *
     * @param command The {@link CreateCoffeeCommand} Command
     * @return A {@link Coffee} instance if the command is valid, otherwise empty
     * @throws IllegalArgumentException if a coffee with the same name already exists (opcional)
     */
    Optional<Coffee> handle(CreateCoffeeCommand command);
}