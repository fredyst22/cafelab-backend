package com.cafemetrix.cafelab.coffees.domain.services;

import com.cafemetrix.cafelab.coffees.domain.model.aggregates.Coffee;
import com.cafemetrix.cafelab.coffees.domain.model.queries.GetAllCoffeesQuery;
import com.cafemetrix.cafelab.coffees.domain.model.queries.GetCoffeeByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Coffee Query Service
 */
public interface CoffeeQueryService {
    /**
     * Handle Get Coffee By ID Query
     *
     * @param query The {@link GetCoffeeByIdQuery} Query
     * @return A {@link Coffee} instance if the query is valid, otherwise empty
     */
    Optional<Coffee> handle(GetCoffeeByIdQuery query);

    /**
     * Handle Get All Coffees Query
     *
     * @param query The {@link GetAllCoffeesQuery} Query
     * @return A list of {@link Coffee} instances
     */
    List<Coffee> handle(GetAllCoffeesQuery query);
}