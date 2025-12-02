package com.verdeemcasa.api.Application.DTOs;

import VerdeEmCasa.Domain.Models.Enums.PlantDifficulty;

public record PlantResponseDto(
        Long id,
        String name,
        String scientificName,
        String description,
        String imageUrl,
        PlantDifficulty difficulty,
        String lightRequirements,
        String waterFrequency,
        Integer wateringIntervalDays,
        boolean active
) {}