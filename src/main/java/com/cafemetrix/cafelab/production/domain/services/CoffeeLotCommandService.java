package com.cafemetrix.cafelab.production.domain.services;

import com.cafemetrix.cafelab.production.domain.model.aggregates.CoffeeLot;
import com.cafemetrix.cafelab.production.domain.model.commands.CreateCoffeeLotCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.DeleteCoffeeLotCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.UpdateCoffeeLotCommand;

import java.util.Optional;

/**
 * CoffeeLot Command Service Interface
 */
public interface CoffeeLotCommandService {
    Optional<CoffeeLot> handle(CreateCoffeeLotCommand command);
    Optional<CoffeeLot> handle(UpdateCoffeeLotCommand command);
    boolean handle(DeleteCoffeeLotCommand command);
} 