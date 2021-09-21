package com.foogaro.data.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

	@Recover
	public List<Long> resultsFallback(RuntimeException re) {
		return numbers;
	}

	@Retryable(recover = "resultsFallback", value = java.net.ConnectException.class, maxAttempts = 1)
	public List<Long> results() {
		List<Long> results = restTemplate.getForObject(backendEndpoint, List.class);
		numbers.clear();
		numbers.addAll(results);
		System.out.println("Updated the list of results.");
		return numbers;
	}

}

