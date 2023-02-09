package com.murtaza.productService.exception;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException {
    private String codeError;

    public ProductNotFoundException(String errorMessage, String codeError) {
        super(errorMessage);
        this.codeError = codeError;
    }
}
