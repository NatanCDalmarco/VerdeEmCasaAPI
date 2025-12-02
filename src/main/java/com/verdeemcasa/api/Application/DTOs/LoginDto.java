package com.verdeemcasa.api.Application.DTOs;

import jakarta.validation.constraints.NotNull;

public record LoginDto(
        @NotNull
        String login,
        @NotNull
        String password
) {
}
