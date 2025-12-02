package com.verdeemcasa.api.Infra.Mapper;

import com.verdeemcasa.api.Application.DTOs.PragaRequestDto;
import com.verdeemcasa.api.Application.DTOs.PragaResponseDto;
import com.verdeemcasa.api.Domain.Models.Praga;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface PragaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true)
    Praga toEntity(PragaRequestDto dto);

    @Mapping(source = "plant.id", target = "plantId")
    @Mapping(source = "plant.name", target = "plantName")
    PragaResponseDto toResponse(Praga entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "plant", ignore = true)
    void updateEntityFromDto(PragaRequestDto dto, @MappingTarget Praga entity);
}