package com.cafemetrix.cafelab.coffees.interfaces.rest.resources;

/**
 * Resource for creating a coffee.
 */
public record CreateCoffeeResource(
        String name,
        String region,
        String variety,
        Double totalWeight
) {
    /**
     * Validates the resource.
     *
     * @throws IllegalArgumentException if the resource is invalid.
     */
    public CreateCoffeeResource {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required");
        if (region == null || region.isBlank()) throw new IllegalArgumentException("Region is required");
        if (variety == null || variety.isBlank()) throw new IllegalArgumentException("Variety is required");
        if (totalWeight == null || totalWeight <= 0) throw new IllegalArgumentException("Total weight must be greater than 0");
    }
}