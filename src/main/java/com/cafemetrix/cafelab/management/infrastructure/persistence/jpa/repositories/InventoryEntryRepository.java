package com.cafemetrix.cafelab.management.infrastructure.persistence.jpa.repositories;

import com.cafemetrix.cafelab.management.domain.model.aggregates.InventoryEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryEntryRepository extends JpaRepository<InventoryEntry, Long> {
    List<InventoryEntry> findByUserId(Long userId);
    List<InventoryEntry> findByCoffeeLotId(Long coffeeLotId);
    List<InventoryEntry> findByUserIdAndCoffeeLotId(Long userId, Long coffeeLotId);
} 