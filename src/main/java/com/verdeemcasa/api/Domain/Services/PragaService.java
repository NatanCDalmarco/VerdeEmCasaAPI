package com.verdeemcasa.api.Domain.Services;

import com.verdeemcasa.api.Application.DTOs.PragaRequestDto;
import com.verdeemcasa.api.Application.DTOs.PragaResponseDto;
import com.verdeemcasa.api.Domain.Models.Plant;
import com.verdeemcasa.api.Domain.Models.Praga;
import com.verdeemcasa.api.Domain.Repositories.PlantRepository;
import com.verdeemcasa.api.Domain.Repositories.PragaRepository;
import com.verdeemcasa.api.Infra.Mapper.PragaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PragaService {

    private final PragaRepository pragaRepository;
    private final PlantRepository plantRepository; // Needed to link the pest to a plant
    private final PragaMapper pragaMapper;

    public PragaService(PragaRepository pragaRepository, PlantRepository plantRepository, PragaMapper pragaMapper) {
        this.pragaRepository = pragaRepository;
        this.plantRepository = plantRepository;
        this.pragaMapper = pragaMapper;
    }

    @Transactional
    public PragaResponseDto create(PragaRequestDto dto) {
        // 1. Find the Plant first
        Plant plant = plantRepository.findById(dto.plantId())
                .orElseThrow(() -> new RuntimeException("Plant not found with ID: " + dto.plantId()));

        // 2. Map DTO to Entity
        Praga praga = pragaMapper.toEntity(dto);

        // 3. Set the relationship
        praga.setPlant(plant);

        // 4. Save
        praga = pragaRepository.save(praga);

        return pragaMapper.toResponse(praga);
    }

    public List<PragaResponseDto> getAll() {
        return pragaRepository.findAll().stream()
                .map(pragaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PragaResponseDto> getAllByPlant(Long plantId) {
        return pragaRepository.findByPlantId(plantId).stream()
                .map(pragaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PragaResponseDto getById(Long id) {
        Praga praga = pragaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pest not found"));
        return pragaMapper.toResponse(praga);
    }

    @Transactional
    public PragaResponseDto update(Long id, PragaRequestDto dto) {
        Praga praga = pragaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pest not found"));

        pragaMapper.updateEntityFromDto(dto, praga);

        // If plant ID changed, we need to update the relationship
        if (dto.plantId() != null && !dto.plantId().equals(praga.getPlant().getId())) {
            Plant newPlant = plantRepository.findById(dto.plantId())
                    .orElseThrow(() -> new RuntimeException("Plant not found"));
            praga.setPlant(newPlant);
        }

        return pragaMapper.toResponse(pragaRepository.save(praga));
    }

    public void delete(Long id) {
        pragaRepository.deleteById(id);
    }
}