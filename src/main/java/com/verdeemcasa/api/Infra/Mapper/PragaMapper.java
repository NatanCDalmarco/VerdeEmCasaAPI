package com.verdeemcasa.api.Infra.Mapper;

import VerdeEmCasa.Application.DTOs.PragaRequestDto;
import VerdeEmCasa.Application.DTOs.PragaResponseDto;
import VerdeEmCasa.Domain.Models.Praga;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface PragaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true) // Handled in Service to load the Entity
    Praga toEntity(PragaRequestDto dto);

    @Mapping(source = "plant.id", target = "plantId")
    @Mapping(source = "plant.name", target = "plantName") // Assuming Plant has a 'name' field
    PragaResponseDto toResponse(Praga entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true)
    void updateEntityFromDto(PragaRequestDto dto, @MappingTarget Praga entity);
}