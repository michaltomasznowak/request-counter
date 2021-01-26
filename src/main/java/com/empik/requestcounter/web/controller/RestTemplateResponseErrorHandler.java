package com.empik.requestcounter.web.controller;

import com.empik.requestcounter.exception.CalculationException;
import com.empik.requestcounter.web.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Collections;

/**
 * michal.nowak created on 24/01/2021
 */
@ControllerAdvice
public class RestTemplateResponseErrorHandler {
    public static final String ERROR_HTTP_CLIENT = "Problem with call request ";
    public static final String ERROR_CALCULATION = "Problem with calculation ";

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ApiError> validateHttpClientErrorException(HttpClientErrorException ex){
        ApiError apiError = ApiError.builder()
                .errors(Collections.singletonList(ERROR_HTTP_CLIENT))
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.valueOf(ex.getRawStatusCode())).build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CalculationException.class)
    public ResponseEntity<ApiError> validateCalculationException(CalculationException ex){
        ApiError apiError = ApiError.builder()
                .errors(Collections.singletonList(ERROR_CALCULATION))
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
