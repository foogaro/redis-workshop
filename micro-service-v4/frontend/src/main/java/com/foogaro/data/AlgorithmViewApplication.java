package com.foogaro.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AlgorithmViewApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgorithmViewApplication.class, args);
	}
}
