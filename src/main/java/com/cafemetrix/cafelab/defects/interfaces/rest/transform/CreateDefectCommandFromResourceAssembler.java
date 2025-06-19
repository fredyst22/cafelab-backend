package com.cafemetrix.cafelab.defects.interfaces.rest.transform;

import com.cafemetrix.cafelab.defects.domain.model.commands.CreateDefectCommand;
import com.cafemetrix.cafelab.defects.interfaces.rest.resources.CreateDefectResource;

/**
 * Assembler to convert a CreateDefectResource to a CreateDefectCommand.
 */
public class CreateDefectCommandFromResourceAssembler {
    /**
     * Converts a CreateDefectResource to a CreateDefectCommand.
     * @param resource The {@link CreateDefectResource} resource to convert.
     * @return The {@link CreateDefectCommand} command.
     */
    public static CreateDefectCommand toCommandFromResource(CreateDefectResource resource) {
        return new CreateDefectCommand(
                resource.coffeeId(),
                resource.name(),
                resource.defectType(),
                resource.defectWeight(),
                resource.percentage(),
                resource.probableCause(),
                resource.suggestedSolution()
        );
    }
}