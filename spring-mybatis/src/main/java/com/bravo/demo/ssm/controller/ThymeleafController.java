package com.bravo.demo.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {

	@RequestMapping("/hellothyme")
	public String helloThymeleaf(Model model) {
		model.addAttribute("greeting", "Hello Thymeleaf!");
		return "greeting";
	}
}
