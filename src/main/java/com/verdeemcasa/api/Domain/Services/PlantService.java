package com.verdeemcasa.api.Domain.Services;

import com.verdeemcasa.api.Application.DTOs.PlantRequestDto;
import com.verdeemcasa.api.Application.DTOs.PlantResponseDto;
import com.verdeemcasa.api.Domain.Models.Plant;
import com.verdeemcasa.api.Domain.Repositories.PlantRepository;
import com.verdeemcasa.api.Infra.Mapper.PlantMapper;
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
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plant not found"));
        plant.setActive(false);
        plantRepository.save(plant);
    }
}