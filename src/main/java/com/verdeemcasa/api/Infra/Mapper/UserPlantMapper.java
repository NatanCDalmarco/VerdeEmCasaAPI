package com.verdeemcasa.api.Infra.Mapper;

import com.verdeemcasa.api.Application.DTOs.UserPlantRequestDto;
import com.verdeemcasa.api.Application.DTOs.UserPlantResponseDto;
import com.verdeemcasa.api.Domain.Models.UserPlant;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface UserPlantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "plant", ignore = true)
    @Mapping(target = "lastWatered", ignore = true)
    @Mapping(target = "nextWatering", ignore = true)
    @Mapping(target = "wateringStatus", ignore = true)
    @Mapping(target = "addedAt", ignore = true)
    UserPlant toEntity(UserPlantRequestDto dto);

    @Mapping(source = "plant.id", target = "plantCatalogId")
    @Mapping(source = "plant.name", target = "plantName")
    @Mapping(source = "plant.imageUrl", target = "plantImageUrl")
    UserPlantResponseDto toResponse(UserPlant entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "plant", ignore = true)
    void updateEntityFromDto(UserPlantRequestDto dto, @MappingTarget UserPlant entity);
}