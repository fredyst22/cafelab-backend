package com.cafemetrix.cafelab.coffees.application.internal.queryservices;

import com.cafemetrix.cafelab.coffees.domain.model.aggregates.Coffee;
import com.cafemetrix.cafelab.coffees.domain.model.queries.GetAllCoffeesQuery;
import com.cafemetrix.cafelab.coffees.domain.model.queries.GetCoffeeByIdQuery;
import com.cafemetrix.cafelab.coffees.domain.services.CoffeeQueryService;
import com.cafemetrix.cafelab.coffees.infrastructure.persistence.jpa.repositories.CoffeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeQueryServiceImpl implements CoffeeQueryService {
    private final CoffeeRepository coffeeRepository;

    public CoffeeQueryServiceImpl(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    @Override
    public Optional<Coffee> handle(GetCoffeeByIdQuery query) {
        return coffeeRepository.findById(query.coffeeId());
    }

    @Override
    public List<Coffee> handle(GetAllCoffeesQuery query) {
        return coffeeRepository.findAll();
    }
}