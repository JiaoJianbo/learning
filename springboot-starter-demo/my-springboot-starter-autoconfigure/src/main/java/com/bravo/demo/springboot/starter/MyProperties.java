package com.bravo.demo.springboot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="my.bravo.demo")
public class MyProperties {
	private String projectDesc = "Project description";

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}
	
}
