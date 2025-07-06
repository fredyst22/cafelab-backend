package com.cafemetrix.cafelab.management.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Resource for creating inventory entry
 */
public record CreateInventoryEntryResource(
    Long userId,
    Long coffeeLotId,
    Double quantityUsed,
    LocalDateTime dateUsed,
    String finalProduct
) {} 