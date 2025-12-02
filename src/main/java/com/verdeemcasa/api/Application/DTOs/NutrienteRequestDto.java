package com.verdeemcasa.api.Application.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NutrienteRequestDto(
        @NotBlank(message = "Nutrient name/type is required")
        String name,

        String description,

        @NotBlank(message = "Application frequency is required")
        String applicationFrequency,

        String dosage,

        @NotNull(message = "Plant ID is required")
        Long plantId
) {}