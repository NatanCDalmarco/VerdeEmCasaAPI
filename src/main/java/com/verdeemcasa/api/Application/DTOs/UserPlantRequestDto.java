package com.verdeemcasa.api.Application.DTOs;

import jakarta.validation.constraints.NotNull;

public record UserPlantRequestDto(
        @NotNull(message = "Plant Catalog ID is required")
        Long plantId, // O ID da planta no catálogo (ex: Espada de São Jorge)

        String nickname, // Opcional
        String location, // Opcional
        String customPhotoUrl // Opcional
) {}