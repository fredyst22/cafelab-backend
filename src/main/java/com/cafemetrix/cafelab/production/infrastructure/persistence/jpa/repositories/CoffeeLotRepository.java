package com.cafemetrix.cafelab.production.infrastructure.persistence.jpa.repositories;

import com.cafemetrix.cafelab.production.domain.model.aggregates.CoffeeLot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoffeeLotRepository extends JpaRepository<CoffeeLot, Long> {
    List<CoffeeLot> findByUserId(Long userId);
    List<CoffeeLot> findBySupplierId(Long supplierId);
    List<CoffeeLot> findByUserIdAndSupplierId(Long userId, Long supplierId);
} 