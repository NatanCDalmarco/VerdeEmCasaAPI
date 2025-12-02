package com.verdeemcasa.api.Application.DTOs;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        boolean active
) {}