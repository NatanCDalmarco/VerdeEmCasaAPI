package com.verdeemcasa.api.Infra.Mapper;

import com.verdeemcasa.api.Application.DTOs.UserRequestDto;
import com.verdeemcasa.api.Application.DTOs.UserResponseDto;
import com.verdeemcasa.api.Domain.Models.User;
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
    @Mapping(target = "password", ignore = true)
    void updateEntityFromDto(UserRequestDto userDto, @MappingTarget User user);
}