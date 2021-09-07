package com.orbsec.photobackend.albumsapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AlbumsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumsApiApplication.class, args);
	}

}
