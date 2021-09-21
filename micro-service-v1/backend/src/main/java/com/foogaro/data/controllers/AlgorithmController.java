package com.foogaro.data.controllers;

import com.foogaro.data.TheAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmController {

	@Autowired
	private TheAlgorithm theAlgorithm;
	
	@GetMapping("/result")
	public long result(@RequestParam(value="maxValue", required = false) Long maxValue) {
		long result = theAlgorithm.result(maxValue);
		System.out.println("Generated " + result + ".");
		return result;
	}
}
