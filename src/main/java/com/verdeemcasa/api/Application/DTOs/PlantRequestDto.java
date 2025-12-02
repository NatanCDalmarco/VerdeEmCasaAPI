package com.verdeemcasa.api.Application.DTOs;

import VerdeEmCasa.Domain.Models.Enums.PlantDifficulty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlantRequestDto(
        @NotBlank(message = "Name is required")
        String name,

        String scientificName,

        String description,

        String imageUrl,

        @NotNull(message = "Difficulty is required")
        PlantDifficulty difficulty,

        @NotBlank(message = "Light requirement is required")
        String lightRequirements,

        @NotBlank(message = "Water frequency description is required")
        String waterFrequency,

        @NotNull(message = "Watering interval is required")
        @Min(1)
        Integer wateringIntervalDays
) {}