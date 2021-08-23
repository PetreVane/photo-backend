package com.orbsec.photobackendusersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PhotoBackendUsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoBackendUsersApiApplication.class, args);
	}

}
