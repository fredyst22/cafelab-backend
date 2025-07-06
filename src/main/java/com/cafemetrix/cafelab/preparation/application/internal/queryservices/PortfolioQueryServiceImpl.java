package com.cafemetrix.cafelab.preparation.application.internal.queryservices;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Portfolio;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetAllPortfoliosQuery;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetPortfolioByIdQuery;
import com.cafemetrix.cafelab.preparation.domain.model.queries.GetPortfoliosByUserIdQuery;
import com.cafemetrix.cafelab.preparation.domain.services.PortfolioQueryService;
import com.cafemetrix.cafelab.preparation.infrastructure.persistence.jpa.repositories.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PortfolioQueryServiceImpl implements PortfolioQueryService {
    private final PortfolioRepository portfolioRepository;

    public PortfolioQueryServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository;
    }

    @Override
    public List<Portfolio> handle(GetAllPortfoliosQuery query) {
        return portfolioRepository.findAll();
    }

    @Override
    public Optional<Portfolio> handle(GetPortfolioByIdQuery query) {
        return portfolioRepository.findById(query.id());
    }

    @Override
    public List<Portfolio> handle(GetPortfoliosByUserIdQuery query) {
        return portfolioRepository.findAllByUserId(query.userId());
    }
} 