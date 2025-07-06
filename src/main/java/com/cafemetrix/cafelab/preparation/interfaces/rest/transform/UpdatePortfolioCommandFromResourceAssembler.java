package com.cafemetrix.cafelab.preparation.interfaces.rest.transform;

import com.cafemetrix.cafelab.preparation.domain.model.commands.UpdatePortfolioCommand;
import com.cafemetrix.cafelab.preparation.interfaces.rest.resources.UpdatePortfolioResource;

/**
 * Assembler for transforming UpdatePortfolioResource to UpdatePortfolioCommand
 */
public class UpdatePortfolioCommandFromResourceAssembler {
    public static UpdatePortfolioCommand toCommandFromResource(Long portfolioId, UpdatePortfolioResource resource) {
        return new UpdatePortfolioCommand(
            portfolioId,
            resource.name()
        );
    }
} 