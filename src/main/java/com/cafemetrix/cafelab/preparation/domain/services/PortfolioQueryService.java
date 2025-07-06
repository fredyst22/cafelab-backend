package com.cafemetrix.cafelab.preparation.domain.services;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Portfolio;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetAllPortfoliosQuery;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetPortfolioByIdQuery;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetPortfoliosByUserIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * Interface for Portfolio Query Service
 */
public interface PortfolioQueryService {
    List<Portfolio> handle(GetAllPortfoliosQuery query);
    Optional<Portfolio> handle(GetPortfolioByIdQuery query);
    List<Portfolio> handle(GetPortfoliosByUserIdQuery query);
} 