package com.cafemetrix.cafelab.defects.domain.model.aggregates;

import com.cafemetrix.cafelab.defects.domain.model.commands.CreateDefectCommand;
import com.cafemetrix.cafelab.defects.domain.model.valueobjects.DefectName;
import com.cafemetrix.cafelab.defects.domain.model.valueobjects.DefectType;
import com.cafemetrix.cafelab.defects.domain.model.valueobjects.ProbableCause;
import com.cafemetrix.cafelab.defects.domain.model.valueobjects.SuggestedSolution;
import com.cafemetrix.cafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * Defect Aggregate Root
 */
@Entity
public class Defect extends AuditableAbstractAggregateRoot<Defect> {

    /**
     * -- GETTER --
     *  Id del café relacionado
     */
    @Getter
    @Column(nullable = false)
    private Long coffeeId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name"))
    private DefectName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "defect_type"))
    private DefectType defectType;

    /**
     * -- GETTER --
     *  Peso del defecto
     */
    @Getter
    @Column(nullable = false)
    private Double defectWeight;


    /**
     * -- GETTER --
     *  Porcentaje del defecto
     */
    @Getter
    @Column(nullable = false)
    private Double percentage;


    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "probable_cause"))
    private ProbableCause probableCause;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "suggested_solution"))
    private SuggestedSolution suggestedSolution;

    /**
     * Constructor principal
     */
    public Defect(Long coffeeId, String name, String defectType, Double defectWeight, Double percentage, String probableCause, String suggestedSolution) {
        this.coffeeId = coffeeId;
        this.name = new DefectName(name);
        this.defectType = new DefectType(defectType);
        this.defectWeight = defectWeight;
        this.percentage = percentage;
        this.probableCause = new ProbableCause(probableCause);
        this.suggestedSolution = new SuggestedSolution(suggestedSolution);
    }

    /**
     * Constructor por defecto
     */
    public Defect() {}

    /**
     * Constructor con comando
     */
    public Defect(CreateDefectCommand command) {
        this.coffeeId = command.coffeeId();
        this.name = new DefectName(command.name());
        this.defectType = new DefectType(command.defectType());
        this.defectWeight = command.defectWeight();
        this.percentage = command.percentage();
        this.probableCause = new ProbableCause(command.probableCause());
        this.suggestedSolution = new SuggestedSolution(command.suggestedSolution());
    }

    /**
     * Nombre del defecto
     */
    public String getName() {
        return name.value();
    }

    /**
     * Tipo de defecto
     */
    public String getDefectType() {
        return defectType.value();
    }

    /**
     * Causa probable
     */
    public String getProbableCause() {
        return probableCause.value();
    }

    /**
     * Solución sugerida
     */
    public String getSuggestedSolution() {
        return suggestedSolution.value();
    }
}