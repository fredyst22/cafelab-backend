package com.cafemetrix.cafelab.coffees.interfaces.rest.transform;

import com.cafemetrix.cafelab.coffees.domain.model.aggregates.Coffee;
import com.cafemetrix.cafelab.coffees.interfaces.rest.resources.CoffeeResource;

/**
 * Assembler to convert a Coffee entity to a CoffeeResource.
 */
public class CoffeeResourceFromEntityAssembler {
    /**
     * Converts a Coffee entity to a CoffeeResource.
     * @param entity The {@link Coffee} entity to convert.
     * @return The {@link CoffeeResource} resource.
     */
    public static CoffeeResource toResourceFromEntity(Coffee entity) {
        return new CoffeeResource(
                entity.getId(),
                entity.getName(),
                entity.getRegion(),
                entity.getVariety(),
                entity.getTotalWeight()
        );
    }
}