package com.cafemetrix.cafelab.production.application.internal.commandservices;

import com.cafemetrix.cafelab.production.domain.model.aggregates.Supplier;
import com.cafemetrix.cafelab.production.domain.model.commands.CreateSupplierCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.DeleteSupplierCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.UpdateSupplierCommand;
import com.cafemetrix.cafelab.production.domain.services.SupplierCommandService;
import com.cafemetrix.cafelab.production.infrastructure.persistence.jpa.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierCommandServiceImpl implements SupplierCommandService {
    private final SupplierRepository supplierRepository;

    public SupplierCommandServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Optional<Supplier> handle(CreateSupplierCommand command) {
        try {
            var supplier = new Supplier(command);
            return Optional.of(supplierRepository.save(supplier));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Supplier> handle(UpdateSupplierCommand command) {
        try {
            var existingSupplier = supplierRepository.findById(command.supplierId());
            if (existingSupplier.isPresent()) {
                var supplier = existingSupplier.get();
                supplier.update(command);
                return Optional.of(supplierRepository.save(supplier));
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean handle(DeleteSupplierCommand command) {
        try {
            if (supplierRepository.existsById(command.supplierId())) {
                supplierRepository.deleteById(command.supplierId());
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
} 