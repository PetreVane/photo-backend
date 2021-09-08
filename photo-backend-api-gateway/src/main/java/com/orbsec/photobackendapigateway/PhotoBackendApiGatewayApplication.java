package com.orbsec.photobackendapigateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orbsec.photobackendapigateway.exceptions.GlobalExceptionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class PhotoBackendApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoBackendApiGatewayApplication.class, args);
    }

    @Bean
    public GlobalExceptionConfiguration globalExceptionHandler(ObjectMapper objectMapper) {
        return new GlobalExceptionConfiguration(objectMapper);
    }

}
