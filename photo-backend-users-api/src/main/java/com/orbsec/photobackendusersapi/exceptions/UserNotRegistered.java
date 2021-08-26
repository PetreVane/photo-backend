package com.orbsec.photobackendusersapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class UserNotRegistered extends RuntimeException {

    public UserNotRegistered(String message) {
        super(message);
    }
}
