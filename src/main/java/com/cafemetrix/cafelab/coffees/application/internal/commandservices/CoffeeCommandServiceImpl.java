package com.cafemetrix.cafelab.coffees.application.internal.commandservices;

import com.cafemetrix.cafelab.coffees.domain.model.aggregates.Coffee;
import com.cafemetrix.cafelab.coffees.domain.model.commands.CreateCoffeeCommand;
import com.cafemetrix.cafelab.coffees.domain.model.valueobjects.CoffeeName;
import com.cafemetrix.cafelab.coffees.domain.services.CoffeeCommandService;
import com.cafemetrix.cafelab.coffees.infrastructure.persistence.jpa.repositories.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Coffee Command Service Implementation
 */
@Service
public class CoffeeCommandServiceImpl implements CoffeeCommandService {
    private final CoffeeRepository coffeeRepository;

    /**
     * Constructor
     *
     * @param coffeeRepository The {@link CoffeeRepository} instance
     */
    public CoffeeCommandServiceImpl(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    // inherited javadoc
    @Override
    public Optional<Coffee> handle(CreateCoffeeCommand command) {
        var coffeeName = new CoffeeName(command.name());
        if (coffeeRepository.existsByName(coffeeName)) {
            throw new IllegalArgumentException("Ya existe un café con ese nombre");
        }
        var coffee = new Coffee(command);
        coffeeRepository.save(coffee);
        return Optional.of(coffee);
    }
}