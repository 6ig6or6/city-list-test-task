package com.andersenlab.citylist.controller;

import com.andersenlab.citylist.dto.UserDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Tag(name = "User controller", description = "Controller for performing operations sign up operations.")
@RequestMapping("/users")
public interface UserController {
    @PostMapping
    ResponseEntity<UserDto> registerUser(@Valid UserDto userDto);
}
