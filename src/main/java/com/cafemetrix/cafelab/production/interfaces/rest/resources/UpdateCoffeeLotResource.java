package com.cafemetrix.cafelab.production.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Resource for updating a coffee lot
 */
public record UpdateCoffeeLotResource(
    @JsonProperty("lot_name")
    @NotBlank(message = "LotName es requerido")
    @Size(max = 100, message = "LotName no puede tener más de 100 caracteres")
    String lot_name,

    @JsonProperty("coffee_type")
    @NotBlank(message = "CoffeeType es requerido")
    @Size(max = 50, message = "CoffeeType no puede tener más de 50 caracteres")
    String coffee_type,

    @JsonProperty("processing_method")
    @NotBlank(message = "ProcessingMethod es requerido")
    @Size(max = 50, message = "ProcessingMethod no puede tener más de 50 caracteres")
    String processing_method,

    @JsonProperty("altitude")
    @NotNull(message = "Altitude es requerido")
    @Positive(message = "Altitude debe ser positivo")
    Integer altitude,

    @JsonProperty("weight")
    @NotNull(message = "Weight es requerido")
    @Positive(message = "Weight debe ser positivo")
    Double weight,

    @JsonProperty("origin")
    @NotBlank(message = "Origin es requerido")
    @Size(max = 100, message = "Origin no puede tener más de 100 caracteres")
    String origin,

    @JsonProperty("status")
    @NotBlank(message = "Status es requerido")
    @Size(max = 20, message = "Status no puede tener más de 20 caracteres")
    String status,

    @JsonProperty("certifications")
    List<String> certifications
) {} 