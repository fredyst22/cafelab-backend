package com.cafemetrix.cafelab.preparation.infrastructure.persistence.jpa.repositories;

import com.cafemetrix.cafelab.preparation.domain.model.aggregates.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    List<Portfolio> findAllByUserId(Long userId);
} 