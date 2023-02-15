package com.andersenlab.citylist.mapper;

import com.andersenlab.citylist.dto.CityDto;
import com.andersenlab.citylist.entity.CityEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "photo", source = "photo")
    CityDto toCityDto(CityEntity cityEntity);

    @InheritInverseConfiguration
    CityEntity toCityEntity(CityDto cityDto);
}
