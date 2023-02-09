package com.murtaza.orderService.external.decoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.murtaza.orderService.exception.CustomException;
import com.murtaza.orderService.external.response.ErrorResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        log.info("::{}", response.request().url());
        log.info("::s{}", response.request().headers());

        ObjectMapper objectMapper = new ObjectMapper();
        ErrorResponse errorResponse;
        try {
            errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new CustomException(errorResponse.getErrorMessage(),errorResponse.getErrorCode(),response.status());
        } catch (IOException e) {
            throw new CustomException("Internal Server error","INTERNAL_SERVER_ERROR",500);
        }

    }
}
