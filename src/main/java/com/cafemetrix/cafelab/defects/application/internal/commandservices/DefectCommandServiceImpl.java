package com.cafemetrix.cafelab.defects.application.internal.commandservices;

import com.cafemetrix.cafelab.defects.domain.model.aggregates.Defect;
import com.cafemetrix.cafelab.defects.domain.model.commands.CreateDefectCommand;
import com.cafemetrix.cafelab.defects.domain.services.DefectCommandService;
import com.cafemetrix.cafelab.defects.infrastructure.persistence.jpa.repositories.DefectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Defect Command Service Implementation
 */
@Service
public class DefectCommandServiceImpl implements DefectCommandService {
    private final DefectRepository defectRepository;

    /**
     * Constructor
     *
     * @param defectRepository The {@link DefectRepository} instance
     */
    public DefectCommandServiceImpl(DefectRepository defectRepository) {
        this.defectRepository = defectRepository;
    }

    // inherited javadoc
    @Override
    public Optional<Defect> handle(CreateDefectCommand command) {
        var defect = new Defect(command);
        defectRepository.save(defect);
        return Optional.of(defect);
    }
}