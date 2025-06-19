package com.cafemetrix.cafelab.coffees.interfaces.rest;

import com.cafemetrix.cafelab.coffees.domain.model.queries.GetAllCoffeesQuery;
import com.cafemetrix.cafelab.coffees.domain.model.queries.GetCoffeeByIdQuery;
import com.cafemetrix.cafelab.coffees.domain.services.CoffeeCommandService;
import com.cafemetrix.cafelab.coffees.domain.services.CoffeeQueryService;
import com.cafemetrix.cafelab.coffees.interfaces.rest.resources.CreateCoffeeResource;
import com.cafemetrix.cafelab.coffees.interfaces.rest.resources.CoffeeResource;
import com.cafemetrix.cafelab.coffees.interfaces.rest.transform.CreateCoffeeCommandFromResourceAssembler;
import com.cafemetrix.cafelab.coffees.interfaces.rest.transform.CoffeeResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CoffeesController
 */
@RestController
@RequestMapping(value = "/api/v1/coffees", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Coffees", description = "Available Coffee Endpoints")
public class CoffeesController {
    private final CoffeeCommandService coffeeCommandService;
    private final CoffeeQueryService coffeeQueryService;

    public CoffeesController(CoffeeCommandService coffeeCommandService, CoffeeQueryService coffeeQueryService) {
        this.coffeeCommandService = coffeeCommandService;
        this.coffeeQueryService = coffeeQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new coffee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Coffee created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<CoffeeResource> createCoffee(@RequestBody CreateCoffeeResource resource) {
        var createCoffeeCommand = CreateCoffeeCommandFromResourceAssembler.toCommandFromResource(resource);
        var coffee = coffeeCommandService.handle(createCoffeeCommand);
        if (coffee.isEmpty()) return ResponseEntity.badRequest().build();
        var createdCoffee = coffee.get();
        var coffeeResource = CoffeeResourceFromEntityAssembler.toResourceFromEntity(createdCoffee);
        return new ResponseEntity<>(coffeeResource, HttpStatus.CREATED);
    }

    @GetMapping("/{coffeeId}")
    @Operation(summary = "Get a coffee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coffee found"),
            @ApiResponse(responseCode = "404", description = "Coffee not found")})
    public ResponseEntity<CoffeeResource> getCoffeeById(@PathVariable Long coffeeId) {
        var getCoffeeByIdQuery = new GetCoffeeByIdQuery(coffeeId);
        var coffee = coffeeQueryService.handle(getCoffeeByIdQuery);
        if (coffee.isEmpty()) return ResponseEntity.notFound().build();
        var coffeeEntity = coffee.get();
        var coffeeResource = CoffeeResourceFromEntityAssembler.toResourceFromEntity(coffeeEntity);
        return ResponseEntity.ok(coffeeResource);
    }

    @GetMapping
    @Operation(summary = "Get all coffees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coffees found"),
            @ApiResponse(responseCode = "404", description = "Coffees not found")})
    public ResponseEntity<List<CoffeeResource>> getAllCoffees() {
        var coffees = coffeeQueryService.handle(new GetAllCoffeesQuery());
        if (coffees.isEmpty()) return ResponseEntity.notFound().build();
        var coffeeResources = coffees.stream()
                .map(CoffeeResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(coffeeResources);
    }
}