package com.orbsec.photobackendapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PhotoBackendApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoBackendApiGatewayApplication.class, args);
	}

}
