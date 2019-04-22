package com.bravo.demo.ssm.security.captcha;

public class CaptchaProperties {
	
	private int width = 60;
	private int height = 22;
	private int size = 4;
	private int expiredInSecond = 60;
	
	private String targetUrl = "";

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getExpiredInSecond() {
		return expiredInSecond;
	}

	public void setExpiredInSecond(int expiredInSecond) {
		this.expiredInSecond = expiredInSecond;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

}
