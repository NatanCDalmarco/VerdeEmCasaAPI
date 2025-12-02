package com.verdeemcasa.api.Application.DTOs;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
        @NotBlank
        String login,
        @NotBlank
        String password
) {}