package com.verdeemcasa.api.Application.DTOs;

import com.verdeemcasa.api.Domain.Models.Enums.WateringStatus;
import java.time.LocalDateTime;

public record UserPlantResponseDto(
        Long id,
        String nickname,
        String location,
        String customPhotoUrl,

        // Dados do Catálogo (para exibir nome científico, foto padrão, etc)
        Long plantCatalogId,
        String plantName,
        String plantImageUrl,

        // Dados de Rega
        LocalDateTime lastWatered,
        LocalDateTime nextWatering,
        WateringStatus wateringStatus
) {}