package com.andersenlab.citylist.mapper;

import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.entity.user.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    UserEntity toUserEntity(UserDto userDto);

    @InheritInverseConfiguration
    UserDto toUserDto(UserEntity userEntity);
}
