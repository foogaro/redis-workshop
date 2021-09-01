package com.foogaro.data.controllers;

import com.foogaro.data.services.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlgorithmController {
	
	@Autowired
	private AlgorithmService algorithmService;

	@GetMapping("/")
	public String view(Model model) {
		model.addAttribute("numbers", algorithmService.getNumbers());
		return "index";
	}

	@GetMapping("/numbers")
	public String moreNumbers(Model model) {
		model.addAttribute("numbers", algorithmService.enlighten());
		return "fragments :: #data";
	}
}
