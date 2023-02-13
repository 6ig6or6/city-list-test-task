package com.andersenlab.citylist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "User with given username already exists!", code = HttpStatus.BAD_REQUEST)
public class UserExistsException extends RuntimeException {
}
