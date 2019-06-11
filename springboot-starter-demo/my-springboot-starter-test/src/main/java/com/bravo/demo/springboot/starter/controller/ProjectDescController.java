package com.bravo.demo.springboot.starter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bravo.demo.springboot.starter.MyProjectService;

@RestController
public class ProjectDescController {
	
	// 在设置里直接使用 starer 中配置的 Bean
	@Autowired
	private MyProjectService myProjectService;

	@GetMapping(value = "/desc")
	public String projectDesc() {
		return myProjectService.getProjectDesc();
	}
}
