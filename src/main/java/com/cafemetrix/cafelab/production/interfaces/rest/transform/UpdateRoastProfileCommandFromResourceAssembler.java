package com.cafemetrix.cafelab.production.interfaces.rest.transform;

import com.cafemetrix.cafelab.production.domain.model.commands.UpdateRoastProfileCommand;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.UpdateRoastProfileResource;

/**
 * Assembler for transforming UpdateRoastProfileResource to UpdateRoastProfileCommand
 */
public class UpdateRoastProfileCommandFromResourceAssembler {
    public static UpdateRoastProfileCommand toCommandFromResource(Long roastProfileId, UpdateRoastProfileResource resource) {
        return new UpdateRoastProfileCommand(
            roastProfileId,
            resource.name(),
            resource.type(),
            resource.duration(),
            resource.tempStart(),
            resource.tempEnd(),
            resource.lot(),
            resource.isFavorite()
        );
    }
} 