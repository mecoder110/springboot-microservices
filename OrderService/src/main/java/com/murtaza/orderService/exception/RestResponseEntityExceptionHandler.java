package com.murtaza.orderService.exception;

import com.murtaza.orderService.external.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException customException){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorMessage(customException.getMessage())
                .errorCode(customException.getCodeError())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf( customException.getStatus()));
    }
}

