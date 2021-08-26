package com.orbsec.photobackendusersapi.exceptions;

import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(UserNotRegistered.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User not registered. An account with this email address already exists.")
    public RegistrationError registrationErrorHandler(UserNotRegistered exception) {
        var error = new RegistrationError();
        error.setErrorMessage(exception.getMessage());
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(System.currentTimeMillis());
        return error;
    }

}
