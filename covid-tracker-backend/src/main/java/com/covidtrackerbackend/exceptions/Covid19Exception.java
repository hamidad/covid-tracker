package com.covidtrackerbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Covid19Exception extends Throwable {
    public Covid19Exception(HttpStatus httpStatus, String message) {
        throw new ResponseStatusException(
                httpStatus, message);
    }
}
