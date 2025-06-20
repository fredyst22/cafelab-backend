package com.cafemetrix.cafelab.profiles.domain.model.commands;

/**
 * Create Profile Command
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
