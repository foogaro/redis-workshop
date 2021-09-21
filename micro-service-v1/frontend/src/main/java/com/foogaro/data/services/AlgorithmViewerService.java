package com.foogaro.data.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmViewerService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired @Qualifier("BACKEND_ENDPOINT")
	private String backendEndpoint;

	@Getter
	private List<Long> numbers = new ArrayList<>();

	private long counter = 1;

	@Recover
	public List<Long> resultsFallback(RuntimeException re) {
		if (counter > 100) counter = 1;
		numbers.add(counter++);
		return numbers;
	}

	@Retryable(recover = "resultsFallback", value = java.net.ConnectException.class, maxAttempts = 1)
	public List<Long> results() {
		long result = restTemplate.getForObject(backendEndpoint, Long.class);
		numbers.add(result);
		System.out.println("Added " + result + " to the list of results.");
		return numbers;
	}

}

