package com.cafemetrix.cafelab.production.interfaces.rest;

import com.cafemetrix.cafelab.production.interfaces.acl.CoffeeproductionContextFacade;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.CreateSupplierResource;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.SupplierResource;
import com.cafemetrix.cafelab.production.interfaces.rest.resources.UpdateSupplierResource;
import com.cafemetrix.cafelab.production.interfaces.rest.transform.UpdateSupplierCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/suppliers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Suppliers", description = "Supplier Management Endpoints")
public class SuppliersController {
    private final CoffeeproductionContextFacade coffeeproductionContextFacade;

    public SuppliersController(CoffeeproductionContextFacade coffeeproductionContextFacade) {
        this.coffeeproductionContextFacade = coffeeproductionContextFacade;
    }

    @Operation(summary = "Create a new supplier")
    @PostMapping
    public ResponseEntity<?> createSupplier(@RequestBody CreateSupplierResource resource) {
        var supplierId = coffeeproductionContextFacade.createSupplier(
            resource.userId(), resource.name(), resource.email(),
            resource.phone(), resource.location(), resource.specialties()
        );

        if (supplierId == 0L) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo crear el proveedor"));
        }

        var suppliers = coffeeproductionContextFacade.getAllSuppliers();
        var supplier = suppliers.stream()
            .filter(s -> s.getId().equals(supplierId))
            .findFirst();

        if (supplier.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener el proveedor creado"));
        }

        var supplierResource = new SupplierResource(
            supplier.get().getId(),
            supplier.get().getUserId(),
            supplier.get().getName(),
            supplier.get().getEmail(),
            supplier.get().getPhone(),
            supplier.get().getLocation(),
            supplier.get().getSpecialties()
        );

        return new ResponseEntity<>(supplierResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all suppliers")
    @GetMapping
    public ResponseEntity<List<SupplierResource>> getAllSuppliers() {
        var suppliers = coffeeproductionContextFacade.getAllSuppliers();
        var supplierResources = suppliers.stream()
            .map(supplier -> new SupplierResource(
                supplier.getId(),
                supplier.getUserId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getLocation(),
                supplier.getSpecialties()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(supplierResources);
    }

    @Operation(summary = "Get suppliers by user ID")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SupplierResource>> getSuppliersByUserId(@PathVariable Long userId) {
        var suppliers = coffeeproductionContextFacade.getSuppliersByUserId(userId);
        var supplierResources = suppliers.stream()
            .map(supplier -> new SupplierResource(
                supplier.getId(),
                supplier.getUserId(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getLocation(),
                supplier.getSpecialties()
            ))
            .collect(Collectors.toList());
        return ResponseEntity.ok(supplierResources);
    }

    @Operation(summary = "Get supplier by ID")
    @GetMapping("/{supplierId}")
    public ResponseEntity<?> getSupplierById(@PathVariable Long supplierId) {
        var supplier = coffeeproductionContextFacade.getSupplierById(supplierId);
        
        if (supplier.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("Proveedor no encontrado"));
        }

        var supplierResource = new SupplierResource(
            supplier.get().getId(),
            supplier.get().getUserId(),
            supplier.get().getName(),
            supplier.get().getEmail(),
            supplier.get().getPhone(),
            supplier.get().getLocation(),
            supplier.get().getSpecialties()
        );

        return ResponseEntity.ok(supplierResource);
    }

    @Operation(summary = "Update a supplier")
    @PutMapping("/{supplierId}")
    public ResponseEntity<?> updateSupplier(
            @PathVariable Long supplierId,
            @RequestBody UpdateSupplierResource resource) {
        
        var updateSupplierCommand = UpdateSupplierCommandFromResourceAssembler.toCommandFromResource(supplierId, resource);
        var updatedSupplierId = coffeeproductionContextFacade.updateSupplier(
            updateSupplierCommand.supplierId(),
            updateSupplierCommand.name(),
            updateSupplierCommand.email(),
            updateSupplierCommand.phone(),
            updateSupplierCommand.location(),
            updateSupplierCommand.specialties()
        );

        if (updatedSupplierId == 0L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo actualizar el proveedor"));
        }

        var suppliers = coffeeproductionContextFacade.getAllSuppliers();
        var supplier = suppliers.stream()
            .filter(s -> s.getId().equals(updatedSupplierId))
            .findFirst();

        if (supplier.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new MessageResource("No se pudo obtener el proveedor actualizado"));
        }

        var supplierResource = new SupplierResource(
            supplier.get().getId(),
            supplier.get().getUserId(),
            supplier.get().getName(),
            supplier.get().getEmail(),
            supplier.get().getPhone(),
            supplier.get().getLocation(),
            supplier.get().getSpecialties()
        );

        return ResponseEntity.ok(supplierResource);
    }

    @Operation(summary = "Delete a supplier")
    @DeleteMapping("/{supplierId}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long supplierId) {
        var deleted = coffeeproductionContextFacade.deleteSupplier(supplierId);
        if (deleted) {
            return ResponseEntity.ok(new MessageResource("Proveedor eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo eliminar el proveedor"));
        }
    }
} 