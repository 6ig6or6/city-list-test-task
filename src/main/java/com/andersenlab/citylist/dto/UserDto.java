package com.andersenlab.citylist.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;

    @Email(message = "Username should be a valid email")
    private String username;

    @Size(min = 10, max = 20, message = "Password length should be between 10 and 20 symbols")
    private String password;
}
