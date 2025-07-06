package com.cafemetrix.cafelab.production.domain.model.commands;

/**
 * Command for deleting a roast profile
 */
public record DeleteRoastProfileCommand(Long roastProfileId) {
    public DeleteRoastProfileCommand {
        if (roastProfileId == null || roastProfileId <= 0) throw new IllegalArgumentException("RoastProfileId es requerido y debe ser positivo");
    }
} 