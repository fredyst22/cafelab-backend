package com.cafemetrix.cafelab.defects.domain.services;

import com.cafemetrix.cafelab.defects.domain.model.aggregates.Defect;
import com.cafemetrix.cafelab.defects.domain.model.commands.CreateDefectCommand;

import java.util.Optional;

/**
 * Defect Command Service
 */
public interface DefectCommandService {
    /**
     * Handle Create Defect Command
     *
     * @param command The {@link CreateDefectCommand} Command
     * @return A {@link Defect} instance if the command is valid, otherwise empty
     * @throws IllegalArgumentException if the defect is invalid or already exists (opcional)
     */
    Optional<Defect> handle(CreateDefectCommand command);
}