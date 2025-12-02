package com.verdeemcasa.api.Infra.Mapper;

import com.verdeemcasa.api.Application.DTOs.PostCommentRequestDto;
import com.verdeemcasa.api.Application.DTOs.PostCommentResponseDto;
import com.verdeemcasa.api.Domain.Models.PostComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "Spring")
public interface PostCommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "post", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    PostComment toEntity(PostCommentRequestDto dto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "author.profilePhotoUrl", target = "authorPhotoUrl")
    PostCommentResponseDto toResponse(PostComment entity);
}