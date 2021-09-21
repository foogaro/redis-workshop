package com.foogaro.data.services;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmViewerService {

	@Autowired
	private RedisStreamService redisStreamService;

	@Getter
	private List<Long> numbers = new ArrayList<>();

	public List<Long> entireStream() {
		List<Long> results = redisStreamService.readEntireStream();
		numbers.clear();
		numbers.addAll(results);
		return numbers;
	}
}

