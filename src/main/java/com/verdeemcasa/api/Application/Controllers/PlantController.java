package com.verdeemcasa.api.Application.Controllers;

import VerdeEmCasa.Application.DTOs.PlantRequestDto;
import VerdeEmCasa.Application.DTOs.PlantResponseDto;
import VerdeEmCasa.Domain.Services.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public ResponseEntity<List<PlantResponseDto>> getAll(@RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(plantService.search(search));
        }
        return ResponseEntity.ok(plantService.getAllActive());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(plantService.getById(id));
    }

    // Admin only endpoint (Security config should restrict this)
    @PostMapping
    public ResponseEntity<PlantResponseDto> create(@RequestBody @Valid PlantRequestDto data, UriComponentsBuilder uriBuilder) {
        var plant = plantService.create(data);
        var uri = uriBuilder.path("/plants/{id}").buildAndExpand(plant.id()).toUri();
        return ResponseEntity.created(uri).body(plant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantResponseDto> update(@PathVariable Long id, @RequestBody @Valid PlantRequestDto data) {
        return ResponseEntity.ok(plantService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        plantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}