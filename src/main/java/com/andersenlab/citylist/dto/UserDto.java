package com.andersenlab.citylist.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;

    @Email
    private String username;

    @Size(min = 10, max = 20)
    private String password;
}
