package com.verdeemcasa.api.Domain.Services;

import VerdeEmCasa.Application.DTOs.PlantRequestDto;
import VerdeEmCasa.Application.DTOs.PlantResponseDto;
import VerdeEmCasa.Domain.Models.Plant;
import VerdeEmCasa.Domain.Repositories.PlantRepository;
import VerdeEmCasa.Infra.Mapper.PlantMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlantService {

    private final PlantRepository plantRepository;
    private final PlantMapper plantMapper;

    public PlantService(PlantRepository plantRepository, PlantMapper plantMapper) {
        this.plantRepository = plantRepository;
        this.plantMapper = plantMapper;
    }

    @Transactional
    public PlantResponseDto create(PlantRequestDto dto) {
        Plant plant = plantMapper.toEntity(dto);
        plant = plantRepository.save(plant);
        return plantMapper.toResponse(plant);
    }

    public List<PlantResponseDto> getAllActive() {
        return plantRepository.findByActiveTrue().stream()
                .map(plantMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Search logic for the catalog page
    public List<PlantResponseDto> search(String query) {
        return plantRepository.findByNameContainingIgnoreCase(query).stream()
                .map(plantMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PlantResponseDto getById(Long id) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found"));
        return plantMapper.toResponse(plant);
    }

    @Transactional
    public PlantResponseDto update(Long id, PlantRequestDto dto) {
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found"));

        plantMapper.updateEntityFromDto(dto, plant);
        return plantMapper.toResponse(plantRepository.save(plant));
    }

    @Transactional
    public void delete(Long id) {
        // Soft delete is better for Catalog items to avoid breaking user history
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found"));
        plant.setActive(false);
        plantRepository.save(plant);
    }
}