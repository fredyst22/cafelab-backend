package com.cafemetrix.cafelab.production.interfaces.rest.transform;

import com.cafemetrix.cafelab.production.domain.model.aggregates.RoastProfile;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.RoastProfileResource;

/**
 * Assembler for transforming RoastProfile entities to RoastProfileResource
 */
public class RoastProfileResourceFromEntityAssembler {
    public static RoastProfileResource toResourceFromEntity(RoastProfile entity) {
        return new RoastProfileResource(
            entity.getId(),
            entity.getUserId(),
            entity.getName(),
            entity.getType(),
            entity.getDuration(),
            entity.getTempStart(),
            entity.getTempEnd(),
            entity.getIsFavorite(),
            entity.getCreatedAt().toString(),
            entity.getCoffeeLotId() // Mapeado como 'lot' en el frontend
        );
    }
} 