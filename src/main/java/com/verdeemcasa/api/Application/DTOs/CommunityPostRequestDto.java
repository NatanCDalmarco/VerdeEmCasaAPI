package com.verdeemcasa.api.Application.DTOs;

import com.verdeemcasa.api.Domain.Models.Enums.PostCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommunityPostRequestDto(
        @NotBlank(message = "O conteúdo não pode estar vazio")
        String content,

        String imageUrl,

        @NotNull(message = "A categoria é obrigatória")
        PostCategory category
) {}