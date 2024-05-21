package com.reactivox.springrxjava.utils.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerError {

    @ExceptionHandler(ErrorHandlerException.class)
    public ResponseEntity<ResponseErrorDto>errorHandler(ErrorHandlerException ex){
        ResponseErrorDto responseErrorDto = ResponseErrorDto
                .builder()
                .httpStatus(ex.getHttpStatus())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(responseErrorDto, responseErrorDto.getHttpStatus());
    }
}
