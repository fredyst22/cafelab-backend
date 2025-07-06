package com.cafemetrix.cafelab.production.application.internal.commandservices;

import com.cafemetrix.cafelab.production.domain.model.aggregates.CoffeeLot;
import com.cafemetrix.cafelab.production.domain.model.commands.CreateCoffeeLotCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.DeleteCoffeeLotCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.UpdateCoffeeLotCommand;
import com.cafemetrix.cafelab.production.domain.services.CoffeeLotCommandService;
import com.cafemetrix.cafelab.production.infrastructure.persistence.jpa.repositories.CoffeeLotRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoffeeLotCommandServiceImpl implements CoffeeLotCommandService {
    private final CoffeeLotRepository coffeeLotRepository;

    public CoffeeLotCommandServiceImpl(CoffeeLotRepository coffeeLotRepository) {
        this.coffeeLotRepository = coffeeLotRepository;
    }

    @Override
    public Optional<CoffeeLot> handle(CreateCoffeeLotCommand command) {
        try {
            var coffeeLot = new CoffeeLot(command);
            return Optional.of(coffeeLotRepository.save(coffeeLot));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<CoffeeLot> handle(UpdateCoffeeLotCommand command) {
        try {
            var existingCoffeeLot = coffeeLotRepository.findById(command.coffeeLotId());
            if (existingCoffeeLot.isPresent()) {
                var coffeeLot = existingCoffeeLot.get();
                coffeeLot.update(command);
                return Optional.of(coffeeLotRepository.save(coffeeLot));
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean handle(DeleteCoffeeLotCommand command) {
        try {
            if (coffeeLotRepository.existsById(command.coffeeLotId())) {
                coffeeLotRepository.deleteById(command.coffeeLotId());
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
} 