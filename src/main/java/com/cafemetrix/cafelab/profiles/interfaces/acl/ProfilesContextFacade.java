package com.cafemetrix.cafelab.profiles.interfaces.acl;

/**
 * Facade for the profiles context
 */
public interface ProfilesContextFacade {
    /**
     * Create a profile
     *
     * @param name           Name of the profile
     * @param email          Email of the profile
     * @param password       Password of the profile
     * @param role           Role of the profile (barista or owner)
     * @param cafeteriaName  Name of the cafeteria (if applicable)
     * @param experience     Years of experience of the profile
     * @param profilePicture URL of the profile picture
     * @param paymentMethod  Payment method of the profile
     * @param isFirstLogin   Indicates if it is the first login of the profile
     * @param plan           Name of the subscribed plan
     * @param hasPlan        Indicates if the profile has an assigned plan
     * @return The id of the created profile if successful, 0 otherwise
     */
    Long createProfile(String name, String email, String password, String role, String cafeteriaName, String experience, String profilePicture, String paymentMethod, boolean isFirstLogin, String plan, boolean hasPlan);

    /**
     * Fetch the profile id by email
     *
     * @param email Email of the profile to fetch
     * @return The id of the profile if found, 0 otherwise
     */
    Long fetchProfileIdByEmail(String email);
}
