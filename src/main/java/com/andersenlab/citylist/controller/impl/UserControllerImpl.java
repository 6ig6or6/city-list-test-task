package com.andersenlab.citylist.controller.impl;

import com.andersenlab.citylist.controller.UserController;
import com.andersenlab.citylist.dto.UserDto;
import com.andersenlab.citylist.mapper.UserMapper;
import com.andersenlab.citylist.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public ResponseEntity<String> registerUser(final UserDto userDto) {
        log.info("Registering user with username {}", userDto.getUsername());
        final UserDto registeredUser = userMapper.toUserDto(userService.registerUser(userDto));
        return new ResponseEntity<>("User with username " + registeredUser.getUsername() + " was registered!", HttpStatus.OK);
    }
}
