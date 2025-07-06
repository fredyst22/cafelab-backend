package com.cafemetrix.cafelab.production.application.internal.queryservices;

import com.cafemetrix.cafelab.production.domain.model.aggregates.Supplier;
import com.cafemetrix.cafelab.production.domain.model.queries.GetAllSuppliersQuery;
import com.cafemetrix.cafelab.production.domain.model.queries.GetSupplierByIdQuery;
import com.cafemetrix.cafelab.production.domain.model.queries.GetSuppliersByUserIdQuery;
import com.cafemetrix.cafelab.production.domain.services.SupplierQueryService;
import com.cafemetrix.cafelab.production.infrastructure.persistence.jpa.repositories.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierQueryServiceImpl implements SupplierQueryService {
    private final SupplierRepository supplierRepository;

    public SupplierQueryServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<Supplier> handle(GetAllSuppliersQuery query) {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> handle(GetSupplierByIdQuery query) {
        return supplierRepository.findById(query.supplierId());
    }

    @Override
    public List<Supplier> handle(GetSuppliersByUserIdQuery query) {
        return supplierRepository.findByUserId(query.userId());
    }
} 