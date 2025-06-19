package com.cafemetrix.cafelab.defects.interfaces.rest.resources;

/**
 * Resource for creating a defect.
 */
public record CreateDefectResource(
        Long coffeeId,
        String name,
        String defectType,
        Double defectWeight,
        Double percentage,
        String probableCause,
        String suggestedSolution
) {
    /**
     * Validates the resource.
     *
     * @throws IllegalArgumentException if the resource is invalid.
     */
    public CreateDefectResource {
        if (coffeeId == null || coffeeId <= 0) throw new IllegalArgumentException("Coffee ID is required and must be greater than 0");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required");
        if (defectType == null || defectType.isBlank()) throw new IllegalArgumentException("Defect type is required");
        if (defectWeight == null || defectWeight <= 0) throw new IllegalArgumentException("Defect weight must be greater than 0");
        if (percentage == null || percentage < 0) throw new IllegalArgumentException("Percentage must be 0 or greater");
        if (probableCause == null || probableCause.isBlank()) throw new IllegalArgumentException("Probable cause is required");
        if (suggestedSolution == null || suggestedSolution.isBlank()) throw new IllegalArgumentException("Suggested solution is required");
    }
}