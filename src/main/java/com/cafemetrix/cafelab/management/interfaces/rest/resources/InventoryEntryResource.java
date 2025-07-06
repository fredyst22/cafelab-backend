package com.cafemetrix.cafelab.management.interfaces.rest.resources;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Resource for inventory entry
 */
public record InventoryEntryResource(
    Long id,
    Long userId,
    Long coffeeLotId,
    Double quantityUsed,
    LocalDateTime dateUsed,
    String finalProduct
) {} 