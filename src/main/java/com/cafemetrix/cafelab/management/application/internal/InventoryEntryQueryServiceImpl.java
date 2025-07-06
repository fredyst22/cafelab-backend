package com.cafemetrix.cafelab.management.application.internal;

import com.cafemetrix.cafelab.management.domain.model.aggregates.InventoryEntry;
import com.cafemetrix.cafelab.management.domain.services.InventoryEntryQueryService;
import com.cafemetrix.cafelab.management.infrastructure.persistence.jpa.repositories.InventoryEntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryEntryQueryServiceImpl implements InventoryEntryQueryService {
    private final InventoryEntryRepository inventoryEntryRepository;

    public InventoryEntryQueryServiceImpl(InventoryEntryRepository inventoryEntryRepository) {
        this.inventoryEntryRepository = inventoryEntryRepository;
    }

    @Override
    public List<InventoryEntry> getAllInventoryEntries() {
        return inventoryEntryRepository.findAll();
    }

    @Override
    public List<InventoryEntry> getInventoryEntriesByUserId(Long userId) {
        return inventoryEntryRepository.findByUserId(userId);
    }

    @Override
    public List<InventoryEntry> getInventoryEntriesByCoffeeLotId(Long coffeeLotId) {
        return inventoryEntryRepository.findByCoffeeLotId(coffeeLotId);
    }

    @Override
    public Optional<InventoryEntry> getInventoryEntryById(Long inventoryEntryId) {
        return inventoryEntryRepository.findById(inventoryEntryId);
    }
} 