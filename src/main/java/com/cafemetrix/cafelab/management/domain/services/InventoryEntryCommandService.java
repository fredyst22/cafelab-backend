package com.cafemetrix.cafelab.management.domain.services;

import com.cafemetrix.cafelab.management.domain.model.aggregates.InventoryEntry;
import com.cafemetrix.cafelab.management.domain.model.commands.CreateInventoryEntryCommand;
import com.cafemetrix.cafelab.management.domain.model.commands.DeleteInventoryEntryCommand;
import com.cafemetrix.cafelab.management.domain.model.commands.UpdateInventoryEntryCommand;

import java.util.Optional;

/**
 * Service interface for inventory entry commands
 */
public interface InventoryEntryCommandService {
    Optional<InventoryEntry> handle(CreateInventoryEntryCommand command);
    Optional<InventoryEntry> handle(UpdateInventoryEntryCommand command);
    boolean handle(DeleteInventoryEntryCommand command);
} 