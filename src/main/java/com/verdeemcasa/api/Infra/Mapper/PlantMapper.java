package com.verdeemcasa.api.Infra.Mapper;

import com.verdeemcasa.api.Application.DTOs.PlantRequestDto;
import com.verdeemcasa.api.Application.DTOs.PlantResponseDto;
import com.verdeemcasa.api.Domain.Models.Plant;
import org.mapstruct.*;

@Mapper(componentModel = "Spring")
public interface PlantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "active", constant = "true")
    Plant toEntity(PlantRequestDto dto);

    PlantResponseDto toResponse(Plant entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(PlantRequestDto dto, @MappingTarget Plant entity);
}