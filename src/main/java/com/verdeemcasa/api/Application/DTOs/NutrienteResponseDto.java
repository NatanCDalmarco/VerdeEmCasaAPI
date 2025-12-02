package com.verdeemcasa.api.Application.DTOs;

public record NutrienteResponseDto(
        Long id,
        String name,
        String description,
        String applicationFrequency,
        String dosage,
        Long plantId,
        String plantName
) {}