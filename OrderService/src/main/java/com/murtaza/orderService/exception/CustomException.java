package com.murtaza.orderService.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private String codeError;
    private int status;
    public CustomException(String errorMessage, String codeError,int status) {
        super(errorMessage);
        this.codeError = codeError;
        this.status = status;

    }
}
