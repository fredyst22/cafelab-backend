package com.cafemetrix.cafelab.production.interfaces.rest.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * Resource for creating a new supplier
 */
public record CreateSupplierResource(
    @JsonProperty("userId")
    @NotNull(message = "UserId es requerido")
    @Positive(message = "UserId debe ser positivo")
    Long userId,

    @JsonProperty("name")
    @NotBlank(message = "Name es requerido")
    @Size(max = 100, message = "Name no puede tener más de 100 caracteres")
    String name,

    @JsonProperty("email")
    @NotBlank(message = "Email es requerido")
    @Email(message = "Email debe tener un formato válido")
    @Size(max = 100, message = "Email no puede tener más de 100 caracteres")
    String email,

    @JsonProperty("phone")
    @NotNull(message = "Phone es requerido")
    @Positive(message = "Phone debe ser positivo")
    Long phone,

    @JsonProperty("location")
    @NotBlank(message = "Location es requerido")
    @Size(max = 200, message = "Location no puede tener más de 200 caracteres")
    String location,

    @JsonProperty("specialties")
    @Size(max = 4, message = "No se pueden tener más de 4 especialidades")
    List<String> specialties
) {} 