package com.reactivox.springrxjava.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ResponseErrorDto {
    private HttpStatus httpStatus;
    private String message;
}
