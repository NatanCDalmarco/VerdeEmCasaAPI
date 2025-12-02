package com.verdeemcasa.api.Application.DTOs;

import jakarta.validation.constraints.NotBlank;

public record PostCommentRequestDto(
        @NotBlank(message = "O comentário não pode ser vazio")
        String content
) {}