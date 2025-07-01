package com.cafemetrix.cafelab.profiles.application.internal.commandservices;

import com.cafemetrix.cafelab.profiles.domain.model.aggregates.Profile;
import com.cafemetrix.cafelab.profiles.domain.model.commands.CreateProfileCommand;
import com.cafemetrix.cafelab.profiles.domain.model.events.ProfileCreatedEvent;
import com.cafemetrix.cafelab.profiles.domain.model.valueobjects.EmailAddress;
import com.cafemetrix.cafelab.profiles.domain.services.ProfileCommandService;
import com.cafemetrix.cafelab.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Profile Command Service Implementation
 */
@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * Constructor
     *
     * @param profileRepository The {@link ProfileRepository} instance
     */
    public ProfileCommandServiceImpl(ProfileRepository profileRepository, ApplicationEventPublisher eventPublisher) {
        this.profileRepository = profileRepository;
        this.eventPublisher = eventPublisher;
    }

    // inherited javadoc
    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        if (profileRepository.existsByEmailAddress(emailAddress)) {
            throw new IllegalArgumentException("Profile with email address already exists");
        }
        var profile = new Profile(command.name(), command.email(), command.password(), command.role(),
                command.cafeteriaName(), command.experience(), command.profilePicture(),
                command.paymentMethod(), command.isFirstLogin(), command.plan(), command.hasPlan());
        profileRepository.save(profile);
        eventPublisher.publishEvent(new ProfileCreatedEvent(command.email(), command.password()));
        return Optional.of(profile);
    }
}
