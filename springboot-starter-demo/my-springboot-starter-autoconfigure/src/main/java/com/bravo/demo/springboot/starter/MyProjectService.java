package com.bravo.demo.springboot.starter;

public class MyProjectService {

	private MyProperties myProperties;
	
	public MyProperties getMyProperties() {
		return myProperties;
	}

	public void setMyProperties(MyProperties myProperties) {
		this.myProperties = myProperties;
	}
	
	
	public String getProjectDesc() {
		return myProperties.getProjectDesc();
	}
}
