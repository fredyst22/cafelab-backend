package com.cafemetrix.cafelab.production.interfaces.rest;

import com.cafemetrix.cafelab.production.interfaces.acl.CoffeeproductionContextFacade;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.CreateRoastProfileResource;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.RoastProfileResource;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.UpdateRoastProfileResource;
import com.cafemetrix.cafelab.production.interfaces.rest.transform.RoastProfileResourceFromEntityAssembler;
import com.cafemetrix.cafelab.production.interfaces.rest.transform.UpdateRoastProfileCommandFromResourceAssembler;
import com.cafemetrix.cafelab.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/roast-profile", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roast Profiles", description = "Roast Profile Management Endpoints")
public class RoastProfilesController {
    private final CoffeeproductionContextFacade coffeeproductionContextFacade;

    public RoastProfilesController(CoffeeproductionContextFacade coffeeproductionContextFacade) {
        this.coffeeproductionContextFacade = coffeeproductionContextFacade;
    }

    @Operation(summary = "Create a new roast profile")
    @PostMapping
    public ResponseEntity<?> createRoastProfile(@RequestBody CreateRoastProfileResource resource) {
        var roastProfileId = coffeeproductionContextFacade.createRoastProfile(
            resource.userId(), resource.name(), resource.type(), resource.duration(),
            resource.tempStart(), resource.tempEnd(), resource.lot(), resource.isFavorite() // lot se mapea a coffeeLotId
        );

        if (roastProfileId == 0L) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo crear el perfil de tueste"));
        }

        var roastProfile = coffeeproductionContextFacade.getRoastProfileById(roastProfileId);
        if (roastProfile.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener el perfil de tueste creado"));
        }

        var roastProfileResource = RoastProfileResourceFromEntityAssembler.toResourceFromEntity(roastProfile.get());
        return new ResponseEntity<>(roastProfileResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all roast profiles")
    @GetMapping
    public ResponseEntity<List<RoastProfileResource>> getAllRoastProfiles() {
        var roastProfiles = coffeeproductionContextFacade.getAllRoastProfiles();
        var roastProfileResources = roastProfiles.stream()
            .map(RoastProfileResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(roastProfileResources);
    }

    @Operation(summary = "Get roast profiles by user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RoastProfileResource>> getRoastProfilesByUserId(@PathVariable Long userId) {
        var roastProfiles = coffeeproductionContextFacade.getRoastProfilesByUserId(userId);
        var roastProfileResources = roastProfiles.stream()
            .map(RoastProfileResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(roastProfileResources);
    }

    @Operation(summary = "Get roast profiles by coffee lot ID")
    @GetMapping("/lot/{coffeeLotId}")
    public ResponseEntity<List<RoastProfileResource>> getRoastProfilesByCoffeeLotId(@PathVariable Long coffeeLotId) {
        var roastProfiles = coffeeproductionContextFacade.getRoastProfilesByCoffeeLotId(coffeeLotId);
        var roastProfileResources = roastProfiles.stream()
            .map(RoastProfileResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(roastProfileResources);
    }

    @Operation(summary = "Get a roast profile by ID")
    @GetMapping("/{roastProfileId}")
    public ResponseEntity<?> getRoastProfileById(@PathVariable Long roastProfileId) {
        var roastProfile = coffeeproductionContextFacade.getRoastProfileById(roastProfileId);
        if (roastProfile.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var roastProfileResource = RoastProfileResourceFromEntityAssembler.toResourceFromEntity(roastProfile.get());
        return ResponseEntity.ok(roastProfileResource);
    }

    @Operation(summary = "Update a roast profile")
    @PutMapping("/{roastProfileId}")
    public ResponseEntity<?> updateRoastProfile(@PathVariable Long roastProfileId, @RequestBody UpdateRoastProfileResource resource) {
        var updateRoastProfileCommand = UpdateRoastProfileCommandFromResourceAssembler.toCommandFromResource(roastProfileId, resource);
        var updatedRoastProfileId = coffeeproductionContextFacade.updateRoastProfile(
            updateRoastProfileCommand.roastProfileId(), updateRoastProfileCommand.name(), updateRoastProfileCommand.type(),
            updateRoastProfileCommand.duration(), updateRoastProfileCommand.tempStart(), updateRoastProfileCommand.tempEnd(),
            updateRoastProfileCommand.coffeeLotId(), updateRoastProfileCommand.isFavorite()
        );

        if (updatedRoastProfileId == 0L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo actualizar el perfil de tueste"));
        }

        var roastProfile = coffeeproductionContextFacade.getRoastProfileById(updatedRoastProfileId);
        if (roastProfile.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener el perfil de tueste actualizado"));
        }

        var roastProfileResource = RoastProfileResourceFromEntityAssembler.toResourceFromEntity(roastProfile.get());
        return ResponseEntity.ok(roastProfileResource);
    }

    @Operation(summary = "Delete a roast profile")
    @DeleteMapping("/{roastProfileId}")
    public ResponseEntity<?> deleteRoastProfile(@PathVariable Long roastProfileId) {
        var deleted = coffeeproductionContextFacade.deleteRoastProfile(roastProfileId);
        if (deleted) {
            return ResponseEntity.ok(new MessageResource("Perfil de tueste eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo eliminar el perfil de tueste"));
        }
    }
} 