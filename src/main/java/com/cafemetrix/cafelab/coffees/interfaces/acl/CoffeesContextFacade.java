package com.cafemetrix.cafelab.coffees.interfaces.acl;

/**
 * CoffeesContextFacade
 */
public interface CoffeesContextFacade {
    /**
     * Create a new coffee
     * @param name The name of the coffee
     * @param region The region of the coffee
     * @param variety The variety of the coffee
     * @param totalWeight The total weight of the coffee
     * @return The coffee ID
     */
    Long createCoffee(String name, String region, String variety, Double totalWeight);

    /**
     * Fetch a coffee ID by name
     * @param name The name of the coffee
     * @return The coffee ID
     */
    Long fetchCoffeeIdByName(String name);
}