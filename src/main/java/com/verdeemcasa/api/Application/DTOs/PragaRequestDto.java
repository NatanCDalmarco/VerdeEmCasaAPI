package com.verdeemcasa.api.Application.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PragaRequestDto(
        @NotBlank(message = "Name is required")
        String name,

        String symptoms,

        String treatment,

        @NotNull(message = "Plant ID is required")
        Long plantId
) {}