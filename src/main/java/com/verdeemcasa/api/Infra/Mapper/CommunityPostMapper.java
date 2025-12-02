package com.verdeemcasa.api.Infra.Mapper;

import com.verdeemcasa.api.Application.DTOs.CommunityPostRequestDto;
import com.verdeemcasa.api.Application.DTOs.CommunityPostResponseDto;
import com.verdeemcasa.api.Domain.Models.CommunityPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface CommunityPostMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "likes", constant = "0")
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "comments", ignore = true)
    CommunityPost toEntity(CommunityPostRequestDto dto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.profilePhotoUrl", target = "authorPhotoUrl")
    CommunityPostResponseDto toResponse(CommunityPost entity);
}