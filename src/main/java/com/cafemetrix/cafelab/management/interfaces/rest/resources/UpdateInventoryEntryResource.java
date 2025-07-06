package com.cafemetrix.cafelab.management.interfaces.rest.resources;

import java.time.LocalDateTime;

/**
 * Resource for updating inventory entry
 */
public record UpdateInventoryEntryResource(
    Long coffeeLotId,
    Double quantityUsed,
    LocalDateTime dateUsed,
    String finalProduct
) {} 