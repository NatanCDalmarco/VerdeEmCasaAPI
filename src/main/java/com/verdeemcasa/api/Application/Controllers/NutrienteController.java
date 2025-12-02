package com.verdeemcasa.api.Application.Controllers;

import com.verdeemcasa.api.Application.DTOs.NutrienteRequestDto;
import com.verdeemcasa.api.Application.DTOs.NutrienteResponseDto;
import com.verdeemcasa.api.Domain.Services.NutrienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/nutrientes")
public class NutrienteController {

    private final NutrienteService nutrienteService;

    public NutrienteController(NutrienteService nutrienteService) {
        this.nutrienteService = nutrienteService;
    }

    @GetMapping("/plant/{plantId}")
    public ResponseEntity<List<NutrienteResponseDto>> getByPlant(@PathVariable Long plantId) {
        return ResponseEntity.ok(nutrienteService.getAllByPlant(plantId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutrienteResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(nutrienteService.getById(id));
    }

    @PostMapping
    public ResponseEntity<NutrienteResponseDto> create(@RequestBody @Valid NutrienteRequestDto data, UriComponentsBuilder uriBuilder) {
        var nutriente = nutrienteService.create(data);
        var uri = uriBuilder.path("/nutrientes/{id}").buildAndExpand(nutriente.id()).toUri();
        return ResponseEntity.created(uri).body(nutriente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutrienteResponseDto> update(@PathVariable Long id, @RequestBody @Valid NutrienteRequestDto data) {
        return ResponseEntity.ok(nutrienteService.update(id, data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nutrienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}