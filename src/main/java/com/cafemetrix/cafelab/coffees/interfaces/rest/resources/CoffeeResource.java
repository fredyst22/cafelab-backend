package com.cafemetrix.cafelab.coffees.interfaces.rest.resources;

/**
 * Resource for a coffee.
 */
public record CoffeeResource(
        Long id,
        String name,
        String region,
        String variety,
        Double totalWeight
) {
}