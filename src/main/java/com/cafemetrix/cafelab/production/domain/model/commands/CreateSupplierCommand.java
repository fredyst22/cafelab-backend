package com.cafemetrix.cafelab.production.domain.model.commands;

import java.util.List;

/**
 * Command for creating a new supplier
 */
public record CreateSupplierCommand(
    Long userId,
    String name,
    String email,
    Long phone,
    String location,
    List<String> specialties
) {
    public CreateSupplierCommand {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name es requerido");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("Email es requerido");
        if (phone == null || phone <= 0) throw new IllegalArgumentException("Phone es requerido y debe ser positivo");
        if (location == null || location.isBlank()) throw new IllegalArgumentException("Location es requerido");
        if (specialties != null && specialties.size() > 4) throw new IllegalArgumentException("No se pueden tener más de 4 especialidades");
    }
} 