package com.cafemetrix.cafelab.preparation.domain.model.commands;

/**
 * Command for updating a portfolio
 */
public record UpdatePortfolioCommand(
    Long portfolioId,
    String name
) {
    public UpdatePortfolioCommand {
        if (portfolioId == null || portfolioId <= 0) throw new IllegalArgumentException("PortfolioId es requerido y debe ser positivo");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name es requerido");
    }
} 