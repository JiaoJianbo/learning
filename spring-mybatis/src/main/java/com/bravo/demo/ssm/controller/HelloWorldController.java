package com.bravo.demo.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bravo.demo.ssm.entity.User;
import com.bravo.demo.ssm.service.UserService;

@RestController
@RequestMapping ("/user")
public class HelloWorldController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/list")
	public List<User> hello() {
		return userService.listAllUser();
	}
}
