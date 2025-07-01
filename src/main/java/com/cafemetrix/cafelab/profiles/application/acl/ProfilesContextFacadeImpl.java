package com.cafemetrix.cafelab.profiles.application.acl;

import com.cafemetrix.cafelab.profiles.domain.model.commands.CreateProfileCommand;
import com.cafemetrix.cafelab.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.cafemetrix.cafelab.profiles.domain.model.valueobjects.EmailAddress;
import com.cafemetrix.cafelab.profiles.domain.services.ProfileCommandService;
import com.cafemetrix.cafelab.profiles.domain.services.ProfileQueryService;
import com.cafemetrix.cafelab.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacadeImpl(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    public Long createProfile(String name,
                              String email,
                              String password,
                              String role,
                              String cafeteriaName,
                              String experience,
                              String profilePicture,
                              String paymentMethod,
                              boolean isFirstLogin,
                              String plan,
                              boolean hasPlan) {
        var createProfileCommand = new CreateProfileCommand(
                name,
                email,
                password,
                role,
                cafeteriaName,
                experience,
                profilePicture,
                paymentMethod,
                isFirstLogin,
                plan,
                hasPlan);
        var profile = profileCommandService.handle(createProfileCommand);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }

    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }
}
