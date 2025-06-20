package com.cafemetrix.cafelab.profiles.domain.model.commands;

/**
 * Update Profile Command
 */
public record UpdateProfileCommand(Long id,
                                   String name,
                                   String email,
                                   String role,
                                   String cafeteriaName,
                                   String experience,
                                   String profilePicture,
                                   String paymentMethod,
                                   String plan,
                                   Boolean hasPlan) {
}
