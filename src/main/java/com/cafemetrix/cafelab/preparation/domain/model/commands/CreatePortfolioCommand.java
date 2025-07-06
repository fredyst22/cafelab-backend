package com.cafemetrix.cafelab.preparation.domain.model.commands;

/**
 * Command for creating a new Portfolio
 */
public record CreatePortfolioCommand(
    Long userId,
    String name
) {
    public CreatePortfolioCommand {
        if (userId == null || userId <= 0) throw new IllegalArgumentException("UserId es requerido y debe ser positivo");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name es requerido");
        if (name.length() > 100) throw new IllegalArgumentException("Name no puede exceder 100 caracteres");
    }
} 