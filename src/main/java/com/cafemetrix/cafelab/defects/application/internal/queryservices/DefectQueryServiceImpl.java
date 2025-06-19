package com.cafemetrix.cafelab.defects.application.internal.queryservices;

import com.cafemetrix.cafelab.defects.domain.model.aggregates.Defect;
import com.cafemetrix.cafelab.defects.domain.model.queries.GetAllDefectsQuery;
import com.cafemetrix.cafelab.defects.domain.model.queries.GetDefectsByCoffeeIdQuery;
import com.cafemetrix.cafelab.defects.domain.services.DefectQueryService;
import com.cafemetrix.cafelab.defects.infrastructure.persistence.jpa.repositories.DefectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Defect Query Service Implementation
 */
@Service
public class DefectQueryServiceImpl implements DefectQueryService {
    private final DefectRepository defectRepository;

    /**
     * Constructor
     *
     * @param defectRepository The {@link DefectRepository} instance
     */
    public DefectQueryServiceImpl(DefectRepository defectRepository) {
        this.defectRepository = defectRepository;
    }

    // inherited javadoc
    @Override
    public List<Defect> handle(GetAllDefectsQuery query) {
        return defectRepository.findAll();
    }

    // inherited javadoc
    @Override
    public List<Defect> handle(GetDefectsByCoffeeIdQuery query) {
        return defectRepository.findByCoffeeId(query.coffeeId());
    }
}