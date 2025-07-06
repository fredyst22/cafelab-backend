package com.cafemetrix.cafelab.preparation.interfaces.rest.transform;

import com.cafemetrix.cafelab.preparation.domain.model.commands.CreateRecipeCommand;
import com.cafemetrix.cafelab.preparation.interfaces.rest.resources.CreateRecipeResource;

/**
 * Assembler for transforming CreateRecipeResource to CreateRecipeCommand
 */
public class CreateRecipeCommandFromResourceAssembler {
    public static CreateRecipeCommand toCommandFromResource(CreateRecipeResource resource) {
        return new CreateRecipeCommand(
            resource.userId(),
            resource.name(),
            resource.imageUrl(),
            resource.extractionMethod(),
            resource.extractionCategory(),
            resource.ratio(),
            resource.cuppingSessionId(),
            resource.portfolioId(),
            resource.preparationTime(),
            resource.steps(),
            resource.tips(),
            resource.cupping(),
            resource.grindSize()
        );
    }
} 