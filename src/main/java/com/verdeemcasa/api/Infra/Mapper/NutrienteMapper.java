package com.verdeemcasa.api.Infra.Mapper;

import com.verdeemcasa.api.Application.DTOs.NutrienteRequestDto;
import com.verdeemcasa.api.Application.DTOs.NutrienteResponseDto;
import com.verdeemcasa.api.Domain.Models.Nutriente;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface NutrienteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true)
    Nutriente toEntity(NutrienteRequestDto dto);

    @Mapping(source = "plant.id", target = "plantId")
    @Mapping(source = "plant.name", target = "plantName")
    NutrienteResponseDto toResponse(Nutriente entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true)
    void updateEntityFromDto(NutrienteRequestDto dto, @MappingTarget Nutriente entity);
}