package com.cafemetrix.cafelab.production.interfaces.rest.resources;

/**
 * Resource for representing a roast profile
 */
public record RoastProfileResource(
    Long id,
    Long userId,
    String name,
    String type,
    Integer duration,
    Double tempStart,
    Double tempEnd,
    Boolean isFavorite,
    String createdAt,
    Long lot
) {} 