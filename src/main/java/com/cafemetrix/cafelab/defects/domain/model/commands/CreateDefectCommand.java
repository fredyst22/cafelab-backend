package com.cafemetrix.cafelab.defects.domain.model.commands;

/**
 * Create Defect Command
 */
public record CreateDefectCommand(
        Long coffeeId,
        String name,
        String defectType,
        Double defectWeight,
        Double percentage,
        String probableCause,
        String suggestedSolution
) {
}