package com.cafemetrix.cafelab.preparation.interfaces.rest;

import com.cafemetrix.cafelab.preparation.interfaces.acl.PreparationContextFacade;
import com.cafemetrix.cafelab.preparation.interfaces.rest.resources.*;
import com.cafemetrix.cafelab.preparation.interfaces.rest.transform.*;
import com.cafemetrix.cafelab.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/portfolios", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Portfolios", description = "Portfolio Management Endpoints")
public class PortfoliosController {
    private final PreparationContextFacade preparationContextFacade;

    public PortfoliosController(PreparationContextFacade preparationContextFacade) {
        this.preparationContextFacade = preparationContextFacade;
    }

    @Operation(summary = "Create a new portfolio")
    @PostMapping
    public ResponseEntity<?> createPortfolio(@RequestBody CreatePortfolioResource resource) {
        var portfolioId = preparationContextFacade.createPortfolio(
            resource.userId(),
            resource.name()
        );

        if (portfolioId == 0L) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo crear el portafolio"));
        }

        var portfolio = preparationContextFacade.getPortfolioById(portfolioId);
        if (portfolio.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener el portafolio creado"));
        }

        var portfolioResource = PortfolioResourceFromEntityAssembler.toResourceFromEntity(portfolio.get());
        return new ResponseEntity<>(portfolioResource, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all portfolios")
    @GetMapping
    public ResponseEntity<List<PortfolioResource>> getAllPortfolios() {
        var portfolios = preparationContextFacade.getAllPortfolios();
        var portfolioResources = portfolios.stream()
            .map(PortfolioResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(portfolioResources);
    }

    @Operation(summary = "Get a portfolio by ID")
    @GetMapping("/{portfolioId}")
    public ResponseEntity<?> getPortfolioById(@PathVariable Long portfolioId) {
        var portfolio = preparationContextFacade.getPortfolioById(portfolioId);
        if (portfolio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var portfolioResource = PortfolioResourceFromEntityAssembler.toResourceFromEntity(portfolio.get());
        return ResponseEntity.ok(portfolioResource);
    }

    @Operation(summary = "Get portfolios by user ID")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PortfolioResource>> getPortfoliosByUserId(@PathVariable Long userId) {
        var portfolios = preparationContextFacade.getPortfoliosByUserId(userId);
        var portfolioResources = portfolios.stream()
            .map(PortfolioResourceFromEntityAssembler::toResourceFromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(portfolioResources);
    }

    @Operation(summary = "Update a portfolio")
    @PutMapping("/{portfolioId}")
    public ResponseEntity<?> updatePortfolio(@PathVariable Long portfolioId, @RequestBody UpdatePortfolioResource resource) {
        var updatePortfolioCommand = UpdatePortfolioCommandFromResourceAssembler.toCommandFromResource(portfolioId, resource);
        var updatedPortfolioId = preparationContextFacade.updatePortfolio(
            updatePortfolioCommand.portfolioId(),
            updatePortfolioCommand.name()
        );

        if (updatedPortfolioId == 0L) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo actualizar el portafolio"));
        }

        var portfolio = preparationContextFacade.getPortfolioById(updatedPortfolioId);
        if (portfolio.isEmpty()) {
            return ResponseEntity.badRequest()
                .body(new MessageResource("No se pudo obtener el portafolio actualizado"));
        }

        var portfolioResource = PortfolioResourceFromEntityAssembler.toResourceFromEntity(portfolio.get());
        return ResponseEntity.ok(portfolioResource);
    }

    @Operation(summary = "Delete a portfolio")
    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<?> deletePortfolio(@PathVariable Long portfolioId) {
        var deleted = preparationContextFacade.deletePortfolio(portfolioId);
        if (deleted) {
            return ResponseEntity.ok(new MessageResource("Portafolio eliminado exitosamente"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MessageResource("No se pudo eliminar el portafolio"));
        }
    }
} 