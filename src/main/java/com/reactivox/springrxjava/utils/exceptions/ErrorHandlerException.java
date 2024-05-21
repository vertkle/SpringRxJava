package com.reactivox.springrxjava.utils.exceptions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorHandlerException extends Exception{
    private HttpStatus httpStatus;

    public ErrorHandlerException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
