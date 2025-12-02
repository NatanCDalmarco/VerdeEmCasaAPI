package com.verdeemcasa.api.Application.DTOs;

import com.verdeemcasa.api.Domain.Models.Enums.PostCategory;
import java.time.LocalDateTime;

public record CommunityPostResponseDto(
        Long id,
        String content,
        String imageUrl,
        PostCategory category,
        Integer likes,
        LocalDateTime createdAt,

        Long authorId,
        String authorName,
        String authorPhotoUrl
) {}