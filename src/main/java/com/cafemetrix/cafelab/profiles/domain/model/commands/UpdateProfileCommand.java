package com.cafemetrix.cafelab.profiles.domain.model.commands;

public record UpdateProfileCommand(
        Long profileId,
        String name,
        String email,
        String cafeteriaName,
        String experience,
        String paymentMethod,
        Boolean isFirstLogin,
        String plan,
        Boolean hasPlan
) {}
