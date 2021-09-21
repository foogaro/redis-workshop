package com.foogaro.data.controllers;

import com.foogaro.data.services.AlgorithmViewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlgorithmViewerController {

	@Autowired
	private AlgorithmViewerService algorithmViewerService;

	@GetMapping("/")
	public String view(Model model) {
		return "index";
	}

	@GetMapping("/numbers")
	public String moreNumbers(Model model) {
        model.addAttribute("numbers", algorithmViewerService.entireStream());
        return "fragments :: #data";
    }

}
