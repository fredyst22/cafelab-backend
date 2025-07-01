package com.cafemetrix.cafelab.profiles.interfaces.rest.transform;

import com.cafemetrix.cafelab.profiles.domain.model.commands.CreateProfileCommand;
import com.cafemetrix.cafelab.profiles.interfaces.rest.resources.CreateProfileResource;

/**
 * Assembler to create a CreateProfileCommand command from a resource
 */
public class CreateProfileCommandFromResourceAssembler {
    /**
     * Create a CreateProfileCommand command from a resource
     *
     * @param resource The {@link CreateProfileResource} resource to create the command from
     * @return The {@link CreateProfileCommand} command created from the resource
     */
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.name(),
                resource.email(),
                resource.password(),
                resource.role(),
                resource.cafeteriaName(),
                resource.experience(),
                resource.profilePicture(),
                resource.paymentMethod(),
                resource.isFirstLogin(),
                resource.plan(),
                resource.hasPlan()
        );
    }
}
