package com.orbsec.photobackenddiscoveryService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class PhotoBackendDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoBackendDiscoveryServiceApplication.class, args);
	}

}
