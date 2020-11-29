package com.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error validateExceptions(MethodArgumentNotValidException exception) {
        Error error = new Error();

        error.status = 400;
        error.error = "Bad Request";

        exception.getBindingResult().getAllErrors().forEach(e -> {
            String msg = e.getDefaultMessage();
            error.message = msg;
        });

        return error;
    }
}
