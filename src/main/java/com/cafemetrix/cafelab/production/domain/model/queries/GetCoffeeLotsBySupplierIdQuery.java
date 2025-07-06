package com.cafemetrix.cafelab.production.domain.model.queries;

/**
 * Query for getting coffee lots by supplier id
 */
public record GetCoffeeLotsBySupplierIdQuery(Long supplierId) {
    public GetCoffeeLotsBySupplierIdQuery {
        if (supplierId == null || supplierId <= 0) throw new IllegalArgumentException("SupplierId es requerido y debe ser positivo");
    }
} 