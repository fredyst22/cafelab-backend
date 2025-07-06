package com.cafemetrix.cafelab.management.domain.services;

import com.cafemetrix.cafelab.management.domain.model.aggregates.InventoryEntry;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for inventory entry queries
 */
public interface InventoryEntryQueryService {
    List<InventoryEntry> getAllInventoryEntries();
    List<InventoryEntry> getInventoryEntriesByUserId(Long userId);
    List<InventoryEntry> getInventoryEntriesByCoffeeLotId(Long coffeeLotId);
    Optional<InventoryEntry> getInventoryEntryById(Long inventoryEntryId);
} 