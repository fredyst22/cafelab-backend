package com.cafemetrix.cafelab.production.domain.model.commands;

/**
 * Command for creating a new roast profile
 */
public record CreateRoastProfileCommand(
    Long userId,
    String name,
    String type,
    Integer duration,
    Double tempStart,
    Double tempEnd,
    Long coffeeLotId,
    Boolean isFavorite
) {
    public CreateRoastProfileCommand {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name es requerido");
        if (type == null || type.isBlank()) throw new IllegalArgumentException("Type es requerido");
        if (duration == null || duration <= 0) throw new IllegalArgumentException("Duration es requerido y debe ser positivo");
        if (tempStart == null) throw new IllegalArgumentException("TempStart es requerido");
        if (tempEnd == null) throw new IllegalArgumentException("TempEnd es requerido");
        if (coffeeLotId == null || coffeeLotId <= 0) throw new IllegalArgumentException("CoffeeLotId es requerido y debe ser positivo");
    }
} 