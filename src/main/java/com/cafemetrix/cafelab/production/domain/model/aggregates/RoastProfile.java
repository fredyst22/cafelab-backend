package com.cafemetrix.cafelab.production.domain.model.aggregates;

import com.cafemetrix.cafelab.production.domain.model.commands.CreateRoastProfileCommand;
import com.cafemetrix.cafelab.production.domain.model.commands.UpdateRoastProfileCommand;
import com.cafemetrix.cafelab.production.domain.model.valueobjects.Duration;
import com.cafemetrix.cafelab.production.domain.model.valueobjects.RoastProfileName;
import com.cafemetrix.cafelab.production.domain.model.valueobjects.RoastType;
import com.cafemetrix.cafelab.production.domain.model.valueobjects.Temperature;
import com.cafemetrix.cafelab.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * RoastProfile Aggregate Root
 */
@Entity
@Table(name = "roast_profiles")
public class RoastProfile extends AuditableAbstractAggregateRoot<RoastProfile> {
    
    @Getter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "name", length = 50))
    private RoastProfileName name;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "type", length = 20))
    private RoastType type;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "duration"))
    private Duration duration;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "temp_start"))
    private Temperature tempStart;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "temp_end"))
    private Temperature tempEnd;

    @Column(name = "is_favorite", nullable = false)
    private Boolean isFavorite;

    @Getter
    @Column(name = "coffee_lot_id", nullable = false)
    private Long coffeeLotId;

    /**
     * Constructor por defecto
     */
    public RoastProfile() {
        this.isFavorite = false;
    }

    /**
     * Constructor principal
     */
    public RoastProfile(Long userId, String name, String type, Integer duration, 
                       Double tempStart, Double tempEnd, Long coffeeLotId) {
        this.userId = userId;
        this.name = new RoastProfileName(name);
        this.type = new RoastType(type);
        this.duration = new Duration(duration);
        this.tempStart = new Temperature(tempStart);
        this.tempEnd = new Temperature(tempEnd);
        this.coffeeLotId = coffeeLotId;
        this.isFavorite = false;
    }

    /**
     * Constructor con comando
     */
    public RoastProfile(CreateRoastProfileCommand command) {
        this.userId = command.userId();
        this.name = new RoastProfileName(command.name());
        this.type = new RoastType(command.type());
        this.duration = new Duration(command.duration());
        this.tempStart = new Temperature(command.tempStart());
        this.tempEnd = new Temperature(command.tempEnd());
        this.coffeeLotId = command.coffeeLotId();
        this.isFavorite = command.isFavorite() != null ? command.isFavorite() : false;
    }

    /**
     * Método para actualizar el perfil de tueste
     */
    public RoastProfile update(UpdateRoastProfileCommand command) {
        this.name = new RoastProfileName(command.name());
        this.type = new RoastType(command.type());
        this.duration = new Duration(command.duration());
        this.tempStart = new Temperature(command.tempStart());
        this.tempEnd = new Temperature(command.tempEnd());
        this.coffeeLotId = command.coffeeLotId();
        this.isFavorite = command.isFavorite();
        return this;
    }

    /**
     * Método para cambiar el estado de favorito
     */
    public void toggleFavorite() {
        this.isFavorite = !this.isFavorite;
    }

    // Getters
    public String getName() { return name.value(); }
    public String getType() { return type.value(); }
    public Integer getDuration() { return duration.value(); }
    public Double getTempStart() { return tempStart.value(); }
    public Double getTempEnd() { return tempEnd.value(); }
    public Boolean getIsFavorite() { return isFavorite; }
} 