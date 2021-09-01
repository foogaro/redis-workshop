package com.foogaro.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlgorithmApplication {

	@Bean
	public TheAlgorithm getTheAlgorithm() {
		return new TheAlgorithm();
	}

	public static void main(String[] args) {
		SpringApplication.run(AlgorithmApplication.class, args);
	}

}
