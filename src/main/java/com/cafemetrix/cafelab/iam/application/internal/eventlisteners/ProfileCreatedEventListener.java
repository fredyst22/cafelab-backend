package com.cafemetrix.cafelab.iam.application.internal.eventlisteners;

import com.cafemetrix.cafelab.iam.domain.model.commands.SignUpCommand;
import com.cafemetrix.cafelab.iam.domain.services.UserCommandService;
import com.cafemetrix.cafelab.profiles.domain.model.events.ProfileCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProfileCreatedEventListener {
    private final UserCommandService userCommandService;

    public ProfileCreatedEventListener(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @EventListener
    public void handle(ProfileCreatedEvent event) {
        var signUpCommand = new SignUpCommand(event.email(), event.password());
        userCommandService.handle(signUpCommand);
    }
}
