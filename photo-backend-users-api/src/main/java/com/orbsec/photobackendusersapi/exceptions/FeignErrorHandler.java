package com.orbsec.photobackendusersapi.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        String errorMessage;

        switch (response.status()) {
            case 400 :
                errorMessage = "Bad request; the server could not respond to your request!";
                break;
            case 404:
                errorMessage = "Resource not found. You are looking for something that does not exists";
                break;
            default:
                errorMessage = response.reason();
        }

        return new ResponseStatusException(HttpStatus.valueOf(response.status()), errorMessage);
    }
}
