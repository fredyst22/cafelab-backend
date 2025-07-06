package com.cafemetrix.cafelab.production.interfaces.rest.resources;

import java.util.List;

/**
 * Resource for representing a supplier
 */
public record SupplierResource(
    Long id,
    Long userId,
    String name,
    String email,
    Long phone,
    String location,
    List<String> specialties
) {} 