package com.cafemetrix.cafelab.iam.interfaces.rest.transform;

import com.cafemetrix.cafelab.iam.domain.model.commands.SignUpCommand;
import com.cafemetrix.cafelab.iam.interfaces.rest.resources.SignUpResource;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(resource.username(), resource.password());
    }
}
