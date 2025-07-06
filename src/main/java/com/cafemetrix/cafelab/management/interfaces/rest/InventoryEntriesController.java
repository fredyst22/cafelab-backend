package com.cafemetrix.cafelab.management.interfaces.rest;

import com.cafemetrix.cafelab.management.interfaces.acl.ManagementContextFacade;
import com.cafemetrix.cafelab.management.interfaces.rest.resources.InventoryEntryResource;
import com.cafemetrix.cafelab.management.interfaces.rest.resources.CreateInventoryEntryResource;
import com.cafemetrix.cafelab.management.interfaces.rest.resources.UpdateInventoryEntryResource;
import com.cafemetrix.cafelab.management.interfaces.rest.transform.UpdateInventoryEntryCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/inventory-entries", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Inventory Entries", description = "Inventory Entry Management Endpoints")
public class InventoryEntriesController {
    private final ManagementContextFacade managementContextFacade;

    public InventoryEntriesController(ManagementContextFacade managementContextFacade) {
        this.managementContextFacade = managementContextFacade;
    }

    @Operation(summary = "Create a new inventory entry")
    @PostMapping
    public ResponseEntity<?> createInventoryEntry(@RequestBody CreateInventoryEntryResource resource) {
        var inventoryEntryId = managementContextFacade.createInventoryEntry(
            resource.userId(), resource.coffeeLotId(), resource.quantityUsed(),
            resource.dateUsed(), resource.finalProduct()
        );

        if (inventoryEntryId == 0L) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo crear la entrada de inventario"));
        }

        var inventoryEntries = managementContextFacade.getAllInventoryEntries();
        var inventoryEntry = inventoryEntries.stream()
            .filter(entry -> entry.getId().equals(inventoryEntryId))
            .findFirst();

        if (inventoryEntry.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener la entrada de inventario creada"));
        }

        var inventoryEntryResource = new InventoryEntryResource(
            inventoryEntry.get().getId(),
            inventoryEntry.get().getUserId(),
            inventoryEntry.get().getCoffeeLotId(),
            inventoryEntry.get().getQuantityUsed(),
            inventoryEntry.get().getDateUsed(),
            inventoryEntry.get().getFinalProduct()
        );

        return new ResponseEntity<>(inventoryEntryResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all inventory entries")
    @GetMapping
    public ResponseEntity<List<InventoryEntryResource>> getAllInventoryEntries() {
        var inventoryEntries = managementContextFacade.getAllInventoryEntries();
        var inventoryEntryResources = inventoryEntries.stream()
            .map(entry -> new InventoryEntryResource(
                entry.getId(),
                entry.getUserId(),
                entry.getCoffeeLotId(),
                entry.getQuantityUsed(),
                entry.getDateUsed(),
                entry.getFinalProduct()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryEntryResources);
    }

    @Operation(summary = "Get inventory entries by user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InventoryEntryResource>> getInventoryEntriesByUserId(@PathVariable Long userId) {
        var inventoryEntries = managementContextFacade.getInventoryEntriesByUserId(userId);
        var inventoryEntryResources = inventoryEntries.stream()
            .map(entry -> new InventoryEntryResource(
                entry.getId(),
                entry.getUserId(),
                entry.getCoffeeLotId(),
                entry.getQuantityUsed(),
                entry.getDateUsed(),
                entry.getFinalProduct()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryEntryResources);
    }

    @Operation(summary = "Get inventory entries by coffee lot ID")
    @GetMapping("/coffee-lot/{coffeeLotId}")
    public ResponseEntity<List<InventoryEntryResource>> getInventoryEntriesByCoffeeLotId(@PathVariable Long coffeeLotId) {
        var inventoryEntries = managementContextFacade.getInventoryEntriesByCoffeeLotId(coffeeLotId);
        var inventoryEntryResources = inventoryEntries.stream()
            .map(entry -> new InventoryEntryResource(
                entry.getId(),
                entry.getUserId(),
                entry.getCoffeeLotId(),
                entry.getQuantityUsed(),
                entry.getDateUsed(),
                entry.getFinalProduct()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(inventoryEntryResources);
    }

    @Operation(summary = "Get inventory entry by ID")
    @GetMapping("/{inventoryEntryId}")
    public ResponseEntity<?> getInventoryEntryById(@PathVariable Long inventoryEntryId) {
        var inventoryEntry = managementContextFacade.getInventoryEntryById(inventoryEntryId);
        
        if (inventoryEntry.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("Entrada de inventario no encontrada"));
        }

        var inventoryEntryResource = new InventoryEntryResource(
            inventoryEntry.get().getId(),
            inventoryEntry.get().getUserId(),
            inventoryEntry.get().getCoffeeLotId(),
            inventoryEntry.get().getQuantityUsed(),
            inventoryEntry.get().getDateUsed(),
            inventoryEntry.get().getFinalProduct()
        );

        return ResponseEntity.ok(inventoryEntryResource);
    }

    @Operation(summary = "Update an inventory entry")
    @PutMapping("/{inventoryEntryId}")
    public ResponseEntity<?> updateInventoryEntry(
            @PathVariable Long inventoryEntryId,
            @RequestBody UpdateInventoryEntryResource resource) {
        
        var updateInventoryEntryCommand = UpdateInventoryEntryCommandFromResourceAssembler.toCommandFromResource(inventoryEntryId, resource);
        var updatedInventoryEntryId = managementContextFacade.updateInventoryEntry(
            updateInventoryEntryCommand.inventoryEntryId(),
            updateInventoryEntryCommand.coffeeLotId(),
            updateInventoryEntryCommand.quantityUsed(),
            updateInventoryEntryCommand.dateUsed(),
            updateInventoryEntryCommand.finalProduct()
        );

        if (updatedInventoryEntryId == 0L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo actualizar la entrada de inventario"));
        }

        var inventoryEntries = managementContextFacade.getAllInventoryEntries();
        var inventoryEntry = inventoryEntries.stream()
            .filter(entry -> entry.getId().equals(updatedInventoryEntryId))
            .findFirst();

        if (inventoryEntry.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource("No se pudo obtener la entrada de inventario actualizada"));
        }

        var inventoryEntryResource = new InventoryEntryResource(
            inventoryEntry.get().getId(),
            inventoryEntry.get().getUserId(),
            inventoryEntry.get().getCoffeeLotId(),
            inventoryEntry.get().getQuantityUsed(),
            inventoryEntry.get().getDateUsed(),
            inventoryEntry.get().getFinalProduct()
        );

        return ResponseEntity.ok(inventoryEntryResource);
    }

    @Operation(summary = "Delete an inventory entry")
    @DeleteMapping("/{inventoryEntryId}")
    public ResponseEntity<?> deleteInventoryEntry(@PathVariable Long inventoryEntryId) {
        var deleted = managementContextFacade.deleteInventoryEntry(inventoryEntryId);
        
        if (deleted) {
            return ResponseEntity.ok(new MessageResource("Entrada de inventario eliminada exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("Entrada de inventario no encontrada"));
        }
    }
} 