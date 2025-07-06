package com.cafemetrix.cafelab.management.interfaces.rest.transform;

import com.cafemetrix.cafelab.management.domain.model.commands.UpdateInventoryEntryCommand;
import com.cafemetrix.cafelab.management.interfaces.rest.resources.UpdateInventoryEntryResource;

/**
 * Assembler to transform UpdateInventoryEntryResource to UpdateInventoryEntryCommand
 */
public class UpdateInventoryEntryCommandFromResourceAssembler {
    public static UpdateInventoryEntryCommand toCommandFromResource(Long inventoryEntryId, UpdateInventoryEntryResource resource) {
        return new UpdateInventoryEntryCommand(
            inventoryEntryId,
            resource.coffeeLotId(),
            resource.quantityUsed(),
            resource.dateUsed(),
            resource.finalProduct()
        );
    }
} 