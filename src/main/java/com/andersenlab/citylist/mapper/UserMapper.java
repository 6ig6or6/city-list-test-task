package com.andersenlab.citylist.mapper;

import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "login", source = "login")
    @Mapping(target = "password", source = "password")
    UserEntity toUserEntity(UserDto userDto);

    @InheritInverseConfiguration
    UserDto toUserDto(UserEntity userEntity);
}
