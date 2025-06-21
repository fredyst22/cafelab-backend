package com.cafemetrix.cafelab.profiles.application.internal.commandservices;

import com.cafemetrix.cafelab.profiles.domain.model.aggregates.Profile;
import com.cafemetrix.cafelab.profiles.domain.model.commands.CreateProfileCommand;
import com.cafemetrix.cafelab.profiles.domain.model.valueobjects.EmailAddress;
import com.cafemetrix.cafelab.profiles.domain.services.ProfileCommandService;
import com.cafemetrix.cafelab.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Profile Command Service Implementation
 */
@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;

    /**
     * Constructor
     *
     * @param profileRepository The {@link ProfileRepository} instance
     */
    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    // inherited javadoc
    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        if (profileRepository.existsByEmailAddress(emailAddress)) {
            throw new IllegalArgumentException("Profile with email address already exists");
        }
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }
}
