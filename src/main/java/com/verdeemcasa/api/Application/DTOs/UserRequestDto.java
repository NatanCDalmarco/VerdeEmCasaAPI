package com.verdeemcasa.api.Application.DTOs;

public record UserRequestDto(
        String name,
        String email,
        String password
) {}