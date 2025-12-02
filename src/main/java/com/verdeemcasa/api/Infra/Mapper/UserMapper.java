package com.verdeemcasa.api.Infra.Mapper;

import VerdeEmCasa.Application.DTOs.UserRequestDto;
import VerdeEmCasa.Application.DTOs.UserResponseDto;
import VerdeEmCasa.Domain.Models.User;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(UserRequestDto userDto);

    UserResponseDto toResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true) // Don't update password via simple update
    void updateEntityFromDto(UserRequestDto userDto, @MappingTarget User user);
}