package com.cafemetrix.cafelab.coffees.interfaces.rest.transform;

import com.cafemetrix.cafelab.coffees.domain.model.commands.CreateCoffeeCommand;
import com.cafemetrix.cafelab.coffees.interfaces.rest.resources.CreateCoffeeResource;

/**
 * Assembler to convert a CreateCoffeeResource to a CreateCoffeeCommand.
 */
public class CreateCoffeeCommandFromResourceAssembler {
    /**
     * Converts a CreateCoffeeResource to a CreateCoffeeCommand.
     * @param resource The {@link CreateCoffeeResource} resource to convert.
     * @return The {@link CreateCoffeeCommand} command.
     */
    public static CreateCoffeeCommand toCommandFromResource(CreateCoffeeResource resource) {
        return new CreateCoffeeCommand(
                resource.name(),
                resource.region(),
                resource.variety(),
                resource.totalWeight()
        );
    }
}