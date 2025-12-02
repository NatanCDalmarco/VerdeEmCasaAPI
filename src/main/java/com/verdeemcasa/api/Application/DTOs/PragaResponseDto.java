package com.verdeemcasa.api.Application.DTOs;

public record PragaResponseDto(
        Long id,
        String name,
        String symptoms,
        String treatment,
        Long plantId,
        String plantName
) {}