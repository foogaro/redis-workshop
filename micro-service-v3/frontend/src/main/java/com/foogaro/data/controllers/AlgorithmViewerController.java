package com.foogaro.data.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class AlgorithmViewerController {


	@GetMapping("/")
	public String view(Model model) {
		model.addAttribute("numbers", new ArrayList<>());
		return "index";
	}

	@GetMapping("/numbers")
	public String moreNumbers(Model model) {
		model.addAttribute("numbers", new ArrayList<>());
        return "fragments :: #data";
    }

}
