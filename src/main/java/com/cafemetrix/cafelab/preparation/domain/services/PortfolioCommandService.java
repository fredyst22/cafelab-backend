package com.cafemetrix.cafelab.preparation.domain.services;

import com.cafemetrix.cafelab.preparation.domain.model.commands.CreatePortfolioCommand;
import com.cafemetrix.cafelab.preparation.domain.model.commands.UpdatePortfolioCommand;
import com.cafemetrix.cafelab.preparation.domain.model.commands.DeletePortfolioCommand;
import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Portfolio;

import java.util.Optional;

/**
 * Interface for Portfolio Command Service
 */
public interface PortfolioCommandService {
    Long handle(CreatePortfolioCommand command);
    Optional<Portfolio> handle(UpdatePortfolioCommand command);
    boolean handle(DeletePortfolioCommand command);
} 