package com.cafemetrix.cafelab.management.domain.model.aggregates;

import com.cafemetrix.cafelab.management.domain.model.commands.CreateInventoryEntryCommand;
import com.cafemetrix.cafelab.management.domain.model.commands.UpdateInventoryEntryCommand;
import com.cafemetrix.cafelab.management.domain.model.valueobjects.*;
import com.cafemetrix.cafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * InventoryEntry Aggregate Root
 */
@Entity
@Table(name = "inventory_entries")
public class InventoryEntry extends AuditableAbstractAggregateRoot<InventoryEntry> {

    @Getter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Getter
    @Column(name = "coffee_lot_id", nullable = false)
    private Long coffeeLotId;

    @Column(name = "quantity_used", nullable = false)
    private Double quantityUsed;

    @Column(name = "date_used", nullable = false)
    private LocalDateTime dateUsed;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "final_product", length = 100))
    private FinalProduct finalProduct;

    /**
     * Constructor por defecto
     */
    public InventoryEntry() {}

    /**
     * Constructor principal
     */
    public InventoryEntry(Long userId, Long coffeeLotId, Double quantityUsed, 
                         LocalDateTime dateUsed, String finalProduct) {
        this.userId = userId;
        this.coffeeLotId = coffeeLotId;
        this.quantityUsed = quantityUsed;
        this.dateUsed = dateUsed;
        this.finalProduct = new FinalProduct(finalProduct);
    }

    /**
     * Constructor con comando
     */
    public InventoryEntry(CreateInventoryEntryCommand command) {
        this.userId = command.userId();
        this.coffeeLotId = command.coffeeLotId();
        this.quantityUsed = command.quantityUsed();
        this.dateUsed = command.dateUsed();
        this.finalProduct = new FinalProduct(command.finalProduct());
    }

    /**
     * Método para actualizar la entrada de inventario
     */
    public InventoryEntry update(UpdateInventoryEntryCommand command) {
        this.coffeeLotId = command.coffeeLotId();
        this.quantityUsed = command.quantityUsed();
        this.dateUsed = command.dateUsed();
        this.finalProduct = new FinalProduct(command.finalProduct());
        return this;
    }

    // Getters
    public Double getQuantityUsed() { return quantityUsed; }
    public LocalDateTime getDateUsed() { return dateUsed; }
    public String getFinalProduct() { return finalProduct.value(); }
} 