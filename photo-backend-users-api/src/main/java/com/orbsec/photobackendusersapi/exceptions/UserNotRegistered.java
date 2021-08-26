package com.orbsec.photobackendusersapi.exceptions;





public class UserNotRegistered extends RuntimeException {

    public UserNotRegistered(String message) {
        super(message);
    }
}
