package com.cafemetrix.cafelab.profiles.domain.model.commands;

/**
 * Create Profile Command WITHOUT PASSWORD
 */
public record CreateProfileCommand(String name,
                                   String email,
                                   String password,
                                   String role,
                                   String cafeteriaName,
                                   String experience,
                                   String profilePicture,
                                   String paymentMethod,
                                   Boolean isFirstLogin,
                                   String plan,
                                   Boolean hasPlan) {
}
