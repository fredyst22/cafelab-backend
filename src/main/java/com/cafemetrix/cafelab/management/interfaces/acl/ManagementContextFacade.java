package com.cafemetrix.cafelab.management.interfaces.acl;

import com.cafemetrix.cafelab.management.domain.model.aggregates.InventoryEntry;
import com.cafemetrix.cafelab.management.domain.model.commands.CreateInventoryEntryCommand;
import com.cafemetrix.cafelab.management.domain.model.commands.DeleteInventoryEntryCommand;
import com.cafemetrix.cafelab.management.domain.model.commands.UpdateInventoryEntryCommand;
import com.cafemetrix.cafelab.management.domain.services.InventoryEntryCommandService;
import com.cafemetrix.cafelab.management.domain.services.InventoryEntryQueryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ManagementContextFacade {
    private final InventoryEntryCommandService inventoryEntryCommandService;
    private final InventoryEntryQueryService inventoryEntryQueryService;

    public ManagementContextFacade(InventoryEntryCommandService inventoryEntryCommandService,
                                 InventoryEntryQueryService inventoryEntryQueryService) {
        this.inventoryEntryCommandService = inventoryEntryCommandService;
        this.inventoryEntryQueryService = inventoryEntryQueryService;
    }

    // Inventory Entry methods
    public Long createInventoryEntry(Long userId, Long coffeeLotId, Double quantityUsed, 
                                   LocalDateTime dateUsed, String finalProduct) {
        var command = new CreateInventoryEntryCommand(userId, coffeeLotId, quantityUsed, dateUsed, finalProduct);
        var result = inventoryEntryCommandService.handle(command);
        return result.map(InventoryEntry::getId).orElse(0L);
    }

    public Long updateInventoryEntry(Long inventoryEntryId, Long coffeeLotId, Double quantityUsed, 
                                   LocalDateTime dateUsed, String finalProduct) {
        var command = new UpdateInventoryEntryCommand(inventoryEntryId, coffeeLotId, quantityUsed, dateUsed, finalProduct);
        var result = inventoryEntryCommandService.handle(command);
        return result.map(InventoryEntry::getId).orElse(0L);
    }

    public boolean deleteInventoryEntry(Long inventoryEntryId) {
        var command = new DeleteInventoryEntryCommand(inventoryEntryId);
        return inventoryEntryCommandService.handle(command);
    }

    public List<InventoryEntry> getAllInventoryEntries() {
        return inventoryEntryQueryService.getAllInventoryEntries();
    }

    public List<InventoryEntry> getInventoryEntriesByUserId(Long userId) {
        return inventoryEntryQueryService.getInventoryEntriesByUserId(userId);
    }

    public List<InventoryEntry> getInventoryEntriesByCoffeeLotId(Long coffeeLotId) {
        return inventoryEntryQueryService.getInventoryEntriesByCoffeeLotId(coffeeLotId);
    }

    public Optional<InventoryEntry> getInventoryEntryById(Long inventoryEntryId) {
        return inventoryEntryQueryService.getInventoryEntryById(inventoryEntryId);
    }
} 