package com.cafemetrix.cafelab.production.interfaces.rest;

import com.cafemetrix.cafelab.production.interfaces.acl.CoffeeproductionContextFacade;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.CoffeeLotResource;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.CreateCoffeeLotResource;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.UpdateCoffeeLotResource;
import com.cafemetrix.cafelab.production.interfaces.rest.transform.UpdateCoffeeLotCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/coffee-lots", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Coffee Lots", description = "Coffee Lot Management Endpoints")
public class CoffeeLotsController {
    private final CoffeeproductionContextFacade coffeeproductionContextFacade;

    public CoffeeLotsController(CoffeeproductionContextFacade coffeeproductionContextFacade) {
        this.coffeeproductionContextFacade = coffeeproductionContextFacade;
    }

    @Operation(summary = "Create a new coffee lot")
    @PostMapping
    public ResponseEntity<?> createCoffeeLot(@RequestBody CreateCoffeeLotResource resource) {
        var coffeeLotId = coffeeproductionContextFacade.createCoffeeLot(
            resource.userId(), resource.supplier_id(), resource.lot_name(), resource.coffee_type(),
            resource.processing_method(), resource.altitude(), resource.weight(), 
            resource.origin(), resource.status(), resource.certifications()
        );

        if (coffeeLotId == 0L) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo crear el lote de café"));
        }

        var coffeeLots = coffeeproductionContextFacade.getAllCoffeeLots();
        var coffeeLot = coffeeLots.stream()
            .filter(lot -> lot.getId().equals(coffeeLotId))
            .findFirst();

        if (coffeeLot.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener el lote de café creado"));
        }

        var coffeeLotResource = new CoffeeLotResource(
            coffeeLot.get().getId(),
            coffeeLot.get().getUserId(),
            coffeeLot.get().getSupplierId(),
            coffeeLot.get().getLotName(),
            coffeeLot.get().getCoffeeType(),
            coffeeLot.get().getProcessingMethod(),
            coffeeLot.get().getAltitude(),
            coffeeLot.get().getWeight(),
            coffeeLot.get().getOrigin(),
            coffeeLot.get().getStatus(),
            coffeeLot.get().getCertifications()
        );

        return new ResponseEntity<>(coffeeLotResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all coffee lots")
    @GetMapping
    public ResponseEntity<List<CoffeeLotResource>> getAllCoffeeLots() {
        var coffeeLots = coffeeproductionContextFacade.getAllCoffeeLots();
        var coffeeLotResources = coffeeLots.stream()
            .map(lot -> new CoffeeLotResource(
                lot.getId(),
                lot.getUserId(),
                lot.getSupplierId(),
                lot.getLotName(),
                lot.getCoffeeType(),
                lot.getProcessingMethod(),
                lot.getAltitude(),
                lot.getWeight(),
                lot.getOrigin(),
                lot.getStatus(),
                lot.getCertifications()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(coffeeLotResources);
    }

    @Operation(summary = "Get coffee lots by user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CoffeeLotResource>> getCoffeeLotsByUserId(@PathVariable Long userId) {
        var coffeeLots = coffeeproductionContextFacade.getCoffeeLotsByUserId(userId);
        var coffeeLotResources = coffeeLots.stream()
            .map(lot -> new CoffeeLotResource(
                lot.getId(),
                lot.getUserId(),
                lot.getSupplierId(),
                lot.getLotName(),
                lot.getCoffeeType(),
                lot.getProcessingMethod(),
                lot.getAltitude(),
                lot.getWeight(),
                lot.getOrigin(),
                lot.getStatus(),
                lot.getCertifications()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(coffeeLotResources);
    }

    @Operation(summary = "Get coffee lots by supplier ID")
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<CoffeeLotResource>> getCoffeeLotsBySupplierId(@PathVariable Long supplierId) {
        var coffeeLots = coffeeproductionContextFacade.getCoffeeLotsBySupplierId(supplierId);
        var coffeeLotResources = coffeeLots.stream()
            .map(lot -> new CoffeeLotResource(
                lot.getId(),
                lot.getUserId(),
                lot.getSupplierId(),
                lot.getLotName(),
                lot.getCoffeeType(),
                lot.getProcessingMethod(),
                lot.getAltitude(),
                lot.getWeight(),
                lot.getOrigin(),
                lot.getStatus(),
                lot.getCertifications()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(coffeeLotResources);
    }

    @Operation(summary = "Get coffee lot by ID")
    @GetMapping("/{coffeeLotId}")
    public ResponseEntity<?> getCoffeeLotById(@PathVariable Long coffeeLotId) {
        var coffeeLot = coffeeproductionContextFacade.getCoffeeLotById(coffeeLotId);
        
        if (coffeeLot.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("Lote de café no encontrado"));
        }

        var coffeeLotResource = new CoffeeLotResource(
            coffeeLot.get().getId(),
            coffeeLot.get().getUserId(),
            coffeeLot.get().getSupplierId(),
            coffeeLot.get().getLotName(),
            coffeeLot.get().getCoffeeType(),
            coffeeLot.get().getProcessingMethod(),
            coffeeLot.get().getAltitude(),
            coffeeLot.get().getWeight(),
            coffeeLot.get().getOrigin(),
            coffeeLot.get().getStatus(),
            coffeeLot.get().getCertifications()
        );

        return ResponseEntity.ok(coffeeLotResource);
    }

    @Operation(summary = "Update a coffee lot")
    @PutMapping("/{coffeeLotId}")
    public ResponseEntity<?> updateCoffeeLot(
            @PathVariable Long coffeeLotId,
            @RequestBody UpdateCoffeeLotResource resource) {
        
        var updateCoffeeLotCommand = UpdateCoffeeLotCommandFromResourceAssembler.toCommandFromResource(coffeeLotId, resource);
        var updatedCoffeeLotId = coffeeproductionContextFacade.updateCoffeeLot(
            updateCoffeeLotCommand.coffeeLotId(),
            updateCoffeeLotCommand.lotName(),
            updateCoffeeLotCommand.coffeeType(),
            updateCoffeeLotCommand.processingMethod(),
            updateCoffeeLotCommand.altitude(),
            updateCoffeeLotCommand.weight(),
            updateCoffeeLotCommand.origin(),
            updateCoffeeLotCommand.status(),
            updateCoffeeLotCommand.certifications()
        );

        if (updatedCoffeeLotId == 0L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo actualizar el lote de café"));
        }

        var coffeeLots = coffeeproductionContextFacade.getAllCoffeeLots();
        var coffeeLot = coffeeLots.stream()
            .filter(lot -> lot.getId().equals(updatedCoffeeLotId))
            .findFirst();

        if (coffeeLot.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource("No se pudo obtener el lote de café actualizado"));
        }

        var coffeeLotResource = new CoffeeLotResource(
            coffeeLot.get().getId(),
            coffeeLot.get().getUserId(),
            coffeeLot.get().getSupplierId(),
            coffeeLot.get().getLotName(),
            coffeeLot.get().getCoffeeType(),
            coffeeLot.get().getProcessingMethod(),
            coffeeLot.get().getAltitude(),
            coffeeLot.get().getWeight(),
            coffeeLot.get().getOrigin(),
            coffeeLot.get().getStatus(),
            coffeeLot.get().getCertifications()
        );

        return ResponseEntity.ok(coffeeLotResource);
    }

    @Operation(summary = "Delete a coffee lot")
    @DeleteMapping("/{coffeeLotId}")
    public ResponseEntity<?> deleteCoffeeLot(@PathVariable Long coffeeLotId) {
        var deleted = coffeeproductionContextFacade.deleteCoffeeLot(coffeeLotId);
        if (deleted) {
            return ResponseEntity.ok(new MessageResource("Lote de café eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo eliminar el lote de café"));
        }
    }
} 