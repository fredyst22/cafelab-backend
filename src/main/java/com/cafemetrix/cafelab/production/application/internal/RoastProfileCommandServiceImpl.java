package com.cafemetrix.cafelab.production.application.internal;

import com.cafemetrix.cafelab.production.domain.model.aggregates.RoastProfile;
import com.cafemetrix.cafelab.production.domain.model.commands.CreateRoastProfileCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.DeleteRoastProfileCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.UpdateRoastProfileCommand;
import com.cafemetrix.cafelab.production.domain.services.RoastProfileCommandService;
import com.cafemetrix.cafelab.production.infrastructure.persistence.jpa.repositories.CoffeeLotRepository;
import com.cafemetrix.cafelab.production.infrastructure.persistence.jpa.repositories.RoastProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoastProfileCommandServiceImpl implements RoastProfileCommandService {
    private final RoastProfileRepository roastProfileRepository;
    private final CoffeeLotRepository coffeeLotRepository;

    public RoastProfileCommandServiceImpl(RoastProfileRepository roastProfileRepository,
                                        CoffeeLotRepository coffeeLotRepository) {
        this.roastProfileRepository = roastProfileRepository;
        this.coffeeLotRepository = coffeeLotRepository;
    }

    @Override
    public Optional<RoastProfile> handle(CreateRoastProfileCommand command) {
        // Validar que el coffee lot existe y pertenece al usuario
        var coffeeLot = coffeeLotRepository.findById(command.coffeeLotId());
        if (coffeeLot.isEmpty()) {
            throw new IllegalArgumentException("El lote de café especificado no existe");
        }
        
        if (!coffeeLot.get().getUserId().equals(command.userId())) {
            throw new IllegalArgumentException("El lote de café no pertenece al usuario especificado");
        }
        
        var roastProfile = new RoastProfile(command);
        var savedRoastProfile = roastProfileRepository.save(roastProfile);
        return Optional.of(savedRoastProfile);
    }

    @Override
    public Optional<RoastProfile> handle(UpdateRoastProfileCommand command) {
        var roastProfile = roastProfileRepository.findById(command.roastProfileId());
        if (roastProfile.isPresent()) {
            // Validar que el coffee lot existe y pertenece al usuario del perfil
            var coffeeLot = coffeeLotRepository.findById(command.coffeeLotId());
            if (coffeeLot.isEmpty()) {
                throw new IllegalArgumentException("El lote de café especificado no existe");
            }
            
            if (!coffeeLot.get().getUserId().equals(roastProfile.get().getUserId())) {
                throw new IllegalArgumentException("El lote de café no pertenece al usuario del perfil");
            }
            
            var updatedRoastProfile = roastProfile.get().update(command);
            var savedRoastProfile = roastProfileRepository.save(updatedRoastProfile);
            return Optional.of(savedRoastProfile);
        }
        return Optional.empty();
    }

    @Override
    public boolean handle(DeleteRoastProfileCommand command) {
        var roastProfile = roastProfileRepository.findById(command.roastProfileId());
        if (roastProfile.isPresent()) {
            roastProfileRepository.deleteById(command.roastProfileId());
            return true;
        }
        return false;
    }
} 