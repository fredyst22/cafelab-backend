package com.cafemetrix.cafelab.defects.interfaces.rest;

import com.cafemetrix.cafelab.defects.domain.model.queries.GetAllDefectsQuery;
import com.cafemetrix.cafelab.defects.domain.model.queries.GetDefectsByCoffeeIdQuery;
import com.cafemetrix.cafelab.defects.domain.services.DefectCommandService;
import com.cafemetrix.cafelab.defects.domain.services.DefectQueryService;
import com.cafemetrix.cafelab.defects.interfaces.rest.resources.CreateDefectResource;
import com.cafemetrix.cafelab.defects.interfaces.rest.resources.DefectResource;
import com.cafemetrix.cafelab.defects.interfaces.rest.transform.CreateDefectCommandFromResourceAssembler;
import com.cafemetrix.cafelab.defects.interfaces.rest.transform.DefectResourceFromEntityAssembler;
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
 * DefectsController
 */
@RestController
@RequestMapping(value = "/api/v1/defects", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Defects", description = "Available Defect Endpoints")
public class DefectsController {
    private final DefectCommandService defectCommandService;
    private final DefectQueryService defectQueryService;

    public DefectsController(DefectCommandService defectCommandService, DefectQueryService defectQueryService) {
        this.defectCommandService = defectCommandService;
        this.defectQueryService = defectQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new defect")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Defect created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<DefectResource> createDefect(@RequestBody CreateDefectResource resource) {
        var createDefectCommand = CreateDefectCommandFromResourceAssembler.toCommandFromResource(resource);
        var defect = defectCommandService.handle(createDefectCommand);
        if (defect.isEmpty()) return ResponseEntity.badRequest().build();
        var createdDefect = defect.get();
        var defectResource = DefectResourceFromEntityAssembler.toResourceFromEntity(createdDefect);
        return new ResponseEntity<>(defectResource, HttpStatus.CREATED);
    }

    @GetMapping("/by-coffee/{coffeeId}")
    @Operation(summary = "Get all defects by coffee ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Defects found"),
            @ApiResponse(responseCode = "404", description = "Defects not found")})
    public ResponseEntity<List<DefectResource>> getDefectsByCoffeeId(@PathVariable Long coffeeId) {
        var getDefectsByCoffeeIdQuery = new GetDefectsByCoffeeIdQuery(coffeeId);
        var defects = defectQueryService.handle(getDefectsByCoffeeIdQuery);
        if (defects.isEmpty()) return ResponseEntity.notFound().build();
        var defectResources = defects.stream()
                .map(DefectResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(defectResources);
    }

    @GetMapping
    @Operation(summary = "Get all defects")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Defects found"),
            @ApiResponse(responseCode = "404", description = "Defects not found")})
    public ResponseEntity<List<DefectResource>> getAllDefects() {
        var defects = defectQueryService.handle(new GetAllDefectsQuery());
        if (defects.isEmpty()) return ResponseEntity.notFound().build();
        var defectResources = defects.stream()
                .map(DefectResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(defectResources);
    }
}