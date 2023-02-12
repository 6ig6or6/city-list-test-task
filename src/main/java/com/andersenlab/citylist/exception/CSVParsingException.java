package com.andersenlab.citylist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Provided csv file is damaged or contains errors and cannot be uploaded", code = HttpStatus.BAD_REQUEST)
public class CSVParsingException extends RuntimeException {
}
