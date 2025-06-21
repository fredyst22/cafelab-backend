package com.cafemetrix.cafelab.profiles.interfaces.rest.resources;

/**
 * Resource for a profile.
 */
public record ProfileResource(
        Long id,
        String name,
        String email,
        String role,
        String cafeteriaName,
        String experience,
        String profilePicture,
        String paymentMethod,
        String plan,
        boolean hasPlan) {
}
