package com.cafemetrix.cafelab.preparation.domain.model.aggregates;

import com.cafemetrix.cafelab.preparation.domain.model.commands.CreatePortfolioCommand;
import com.cafemetrix.cafelab.preparation.domain.model.commands.UpdatePortfolioCommand;
import com.cafemetrix.cafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * Portfolio Aggregate Root
 */
@Entity
@Table(name = "portfolios")
public class Portfolio extends AuditableAbstractAggregateRoot<Portfolio> {
    
    @Getter
    @Column(nullable = false)
    private Long userId;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    public Portfolio() {}

    public Portfolio(Long userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public Portfolio(CreatePortfolioCommand command) {
        this.userId = command.userId();
        this.name = command.name();
    }

    /**
     * Método para actualizar el portfolio
     */
    public Portfolio update(UpdatePortfolioCommand command) {
        this.name = command.name();
        return this;
    }

    public String getName() {
        return name;
    }
} 