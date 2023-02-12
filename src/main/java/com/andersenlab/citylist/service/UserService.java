package com.andersenlab.citylist.service;

import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.entity.UserEntity;

public interface UserService {
    UserEntity registerUser(UserDto userDto);
}
