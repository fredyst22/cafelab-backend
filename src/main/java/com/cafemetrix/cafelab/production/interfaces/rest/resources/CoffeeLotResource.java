package com.cafemetrix.cafelab.production.interfaces.rest.resources;

import java.util.List;

/**
 * Resource for representing a coffee lot
 */
public record CoffeeLotResource(
    Long id,
    Long userId,
    Long supplier_id,
    String lot_name,
    String coffee_type,
    String processing_method,
    Integer altitude,
    Double weight,
    String origin,
    String status,
    List<String> certifications
) {} 