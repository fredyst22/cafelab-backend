package com.cafemetrix.cafelab.defects.interfaces.rest.resources;

/**
 * Resource for a defect.
 */
public record DefectResource(
        Long id,
        Long coffeeId,
        String name,
        String defectType,
        Double defectWeight,
        Double percentage,
        String probableCause,
        String suggestedSolution
) {
}