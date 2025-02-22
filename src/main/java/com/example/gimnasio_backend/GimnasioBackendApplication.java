package com.example.gimnasio_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GimnasioBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(GimnasioBackendApplication.class, args);
	}

}
