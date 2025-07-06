package com.cafemetrix.cafelab.production.domain.model.queries;

/**
 * Query for getting a supplier by id
 */
public record GetSupplierByIdQuery(Long supplierId) {
    public GetSupplierByIdQuery {
        if (supplierId == null || supplierId <= 0) throw new IllegalArgumentException("SupplierId es requerido y debe ser positivo");
    }
} 