package com.cafemetrix.cafelab.production.interfaces.rest.transform;

import com.cafemetrix.cafelab.production.domain.model.commands.CreateRoastProfileCommand;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.CreateRoastProfileResource;

/**
 * Assembler for transforming CreateRoastProfileResource to CreateRoastProfileCommand
 */
public class CreateRoastProfileCommandFromResourceAssembler {
    public static CreateRoastProfileCommand toCommandFromResource(CreateRoastProfileResource resource) {
        return new CreateRoastProfileCommand(
            resource.userId(),
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