package com.cafemetrix.cafelab.preparation.interfaces.rest.transform;

import com.cafemetrix.cafelab.preparation.domain.model.commands.CreatePortfolioCommand;
import com.cafemetrix.cafelab.preparation.interfaces.rest.resources.CreatePortfolioResource;

/**
 * Assembler for transforming CreatePortfolioResource to CreatePortfolioCommand
 */
public class CreatePortfolioCommandFromResourceAssembler {
    public static CreatePortfolioCommand toCommandFromResource(CreatePortfolioResource resource) {
        return new CreatePortfolioCommand(
            resource.userId(),
            resource.name()
        );
    }
} 