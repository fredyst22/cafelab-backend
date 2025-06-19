package com.cafemetrix.cafelab.defects.interfaces.rest.transform;

import com.cafemetrix.cafelab.defects.domain.model.aggregates.Defect;
import com.cafemetrix.cafelab.defects.interfaces.rest.resources.DefectResource;

/**
 * Assembler to convert a Defect entity to a DefectResource.
 */
public class DefectResourceFromEntityAssembler {
    /**
     * Converts a Defect entity to a DefectResource.
     * @param entity The {@link Defect} entity to convert.
     * @return The {@link DefectResource} resource.
     */
    public static DefectResource toResourceFromEntity(Defect entity) {
        return new DefectResource(
                entity.getId(),
                entity.getCoffeeId(),
                entity.getName(),
                entity.getDefectType(),
                entity.getDefectWeight(),
                entity.getPercentage(),
                entity.getProbableCause(),
                entity.getSuggestedSolution()
        );
    }
}