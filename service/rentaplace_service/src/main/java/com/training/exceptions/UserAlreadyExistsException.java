package com.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User Already Exist")
public class UserAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
}
