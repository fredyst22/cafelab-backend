package com.cafemetrix.cafelab.defects.domain.services;

import com.cafemetrix.cafelab.defects.domain.model.aggregates.Defect;
import com.cafemetrix.cafelab.defects.domain.model.queries.GetAllDefectsQuery;
import com.cafemetrix.cafelab.defects.domain.model.queries.GetDefectsByCoffeeIdQuery;

import java.util.List;

/**
 * Defect Query Service
 */
public interface DefectQueryService {
    /**
     * Handle Get All Defects Query
     *
     * @param query The {@link GetAllDefectsQuery} Query
     * @return A list of {@link Defect} instances
     */
    List<Defect> handle(GetAllDefectsQuery query);

    /**
     * Handle Get Defects By CoffeeId Query
     *
     * @param query The {@link GetDefectsByCoffeeIdQuery} Query
     * @return A list of {@link Defect} instances related to the given coffeeId
     */
    List<Defect> handle(GetDefectsByCoffeeIdQuery query);
}