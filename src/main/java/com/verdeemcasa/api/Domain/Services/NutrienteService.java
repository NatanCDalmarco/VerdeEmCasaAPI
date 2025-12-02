package com.verdeemcasa.api.Domain.Services;

import VerdeEmCasa.Application.DTOs.NutrienteRequestDto;
import VerdeEmCasa.Application.DTOs.NutrienteResponseDto;
import VerdeEmCasa.Domain.Models.Nutriente;
import VerdeEmCasa.Domain.Models.Plant;
import VerdeEmCasa.Domain.Repositories.NutrienteRepository;
import VerdeEmCasa.Domain.Repositories.PlantRepository;
import VerdeEmCasa.Infra.Mapper.NutrienteMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NutrienteService {

    private final NutrienteRepository nutrienteRepository;
    private final PlantRepository plantRepository;
    private final NutrienteMapper nutrienteMapper;

    public NutrienteService(NutrienteRepository nutrienteRepository, PlantRepository plantRepository, NutrienteMapper nutrienteMapper) {
        this.nutrienteRepository = nutrienteRepository;
        this.plantRepository = plantRepository;
        this.nutrienteMapper = nutrienteMapper;
    }

    @Transactional
    public NutrienteResponseDto create(NutrienteRequestDto dto) {
        // 1. Validate Plant existence
        Plant plant = plantRepository.findById(dto.plantId())
                .orElseThrow(() -> new RuntimeException("Plant not found with ID: " + dto.plantId()));

        // 2. Map and Set
        Nutriente nutriente = nutrienteMapper.toEntity(dto);
        nutriente.setPlant(plant);

        // 3. Save
        return nutrienteMapper.toResponse(nutrienteRepository.save(nutriente));
    }

    public List<NutrienteResponseDto> getAllByPlant(Long plantId) {
        return nutrienteRepository.findByPlantId(plantId).stream()
                .map(nutrienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public NutrienteResponseDto getById(Long id) {
        Nutriente nutriente = nutrienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutrient not found"));
        return nutrienteMapper.toResponse(nutriente);
    }

    @Transactional
    public NutrienteResponseDto update(Long id, NutrienteRequestDto dto) {
        Nutriente nutriente = nutrienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nutrient not found"));

        nutrienteMapper.updateEntityFromDto(dto, nutriente);

        if (dto.plantId() != null && !dto.plantId().equals(nutriente.getPlant().getId())) {
            Plant newPlant = plantRepository.findById(dto.plantId())
                    .orElseThrow(() -> new RuntimeException("Plant not found"));
            nutriente.setPlant(newPlant);
        }

        return nutrienteMapper.toResponse(nutrienteRepository.save(nutriente));
    }

    public void delete(Long id) {
        nutrienteRepository.deleteById(id);
    }
}