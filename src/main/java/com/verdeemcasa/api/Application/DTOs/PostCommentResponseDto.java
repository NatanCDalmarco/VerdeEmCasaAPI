package com.verdeemcasa.api.Application.DTOs;

import java.time.LocalDateTime;

public record PostCommentResponseDto(
        Long id,
        String content,
        LocalDateTime createdAt,

        Long authorId,
        String authorName,
        String authorPhotoUrl
) {}