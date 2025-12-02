package com.verdeemcasa.api.Application.Controllers;

import VerdeEmCasa.Application.DTOs.PragaRequestDto;
import VerdeEmCasa.Application.DTOs.PragaResponseDto;
import VerdeEmCasa.Domain.Services.PragaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/pragas")
public class PragaController {

    private final PragaService pragaService;

    public PragaController(PragaService pragaService) {
        this.pragaService = pragaService;
    }

    @GetMapping
    public ResponseEntity<List<PragaResponseDto>> getAll() {
        return ResponseEntity.ok(pragaService.getAll());
    }

    // New Endpoint: Get pests specific to a plant
    // Example: GET /pragas/plant/1
    @GetMapping("/plant/{plantId}")
    public ResponseEntity<List<PragaResponseDto>> getByPlant(@PathVariable Long plantId) {
        return ResponseEntity.ok(pragaService.getAllByPlant(plantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PragaResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(pragaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PragaResponseDto> create(@RequestBody @Valid PragaRequestDto data, UriComponentsBuilder uriBuilder) {
        var praga = pragaService.create(data);
        var uri = uriBuilder.path("/pragas/{id}").buildAndExpand(praga.id()).toUri();
        return ResponseEntity.created(uri).body(praga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PragaResponseDto> update(@PathVariable Long id, @RequestBody @Valid PragaRequestDto data) {
        return ResponseEntity.ok(pragaService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pragaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}