package com.cafemetrix.cafelab.defects.interfaces.acl;

import java.util.List;

/**
 * DefectsContextFacade
 */
public interface DefectsContextFacade {
    /**
     * Create a new defect
     * @param coffeeId The ID of the related coffee
     * @param name The name of the defect
     * @param defectType The type of the defect
     * @param defectWeight The weight of the defect
     * @param percentage The percentage of the defect
     * @param probableCause The probable cause of the defect
     * @param suggestedSolution The suggested solution for the defect
     * @return The defect ID
     */
    Long createDefect(Long coffeeId, String name, String defectType, Double defectWeight, Double percentage, String probableCause, String suggestedSolution);

    /**
     * Fetch all defects by coffee ID
     * @param coffeeId The ID of the related coffee
     * @return A list of defect IDs
     */
    List<Long> fetchDefectIdsByCoffeeId(Long coffeeId);
}