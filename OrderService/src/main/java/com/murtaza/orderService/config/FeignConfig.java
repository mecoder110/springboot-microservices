package com.murtaza.orderService.config;

import com.murtaza.orderService.external.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    ErrorDecoder errorDecoder(){
        System.out.println("DECODER========================================");
        return new CustomErrorDecoder();
    }
}
