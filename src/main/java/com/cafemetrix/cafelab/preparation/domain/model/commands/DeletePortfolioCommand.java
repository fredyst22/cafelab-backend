package com.cafemetrix.cafelab.preparation.domain.model.commands;

/**
 * Command for deleting a portfolio
 */
public record DeletePortfolioCommand(
    Long portfolioId
) {
    public DeletePortfolioCommand {
        if (portfolioId == null || portfolioId <= 0) throw new IllegalArgumentException("PortfolioId es requerido y debe ser positivo");
    }
} 