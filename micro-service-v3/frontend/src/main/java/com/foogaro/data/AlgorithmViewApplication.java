package com.foogaro.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@EnableRetry
@SpringBootApplication
public class AlgorithmViewApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
			.setConnectTimeout(Duration.ofSeconds(500))
			.setReadTimeout(Duration.ofSeconds(500))
			.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(AlgorithmViewApplication.class, args);
	}
}
