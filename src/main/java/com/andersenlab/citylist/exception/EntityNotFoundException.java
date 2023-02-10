package com.andersenlab.citylist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Entity with the given id doesn't exist in the database!", code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {
}
