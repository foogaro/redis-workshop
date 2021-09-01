package com.foogaro.data.services;

import com.foogaro.data.TheAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmService {

	@Autowired
	private TheAlgorithm theAlgorithm;

	@Getter
	private List<Long> numbers = new ArrayList<>();

	public List<Long> enlighten() {
		numbers.add(theAlgorithm.result());
		return numbers;
	}

}

