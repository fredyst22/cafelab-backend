package com.cafemetrix.cafelab.preparation.interfaces.rest.transform;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Portfolio;
import com.cafemetrix.cafelab.preparation.interfaces.rest.resources.PortfolioResource;

/**
 * Assembler for transforming Portfolio entities to PortfolioResource
 */
public class PortfolioResourceFromEntityAssembler {
    public static PortfolioResource toResourceFromEntity(Portfolio entity) {
        return new PortfolioResource(
            entity.getId(),
            entity.getUserId(),
            entity.getName(),
            entity.getCreatedAt().toString()
        );
    }
} 