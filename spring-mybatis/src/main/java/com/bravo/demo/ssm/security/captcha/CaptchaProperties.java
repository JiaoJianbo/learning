package com.bravo.demo.ssm.security.captcha;

public class CaptchaProperties {
	
	private int width = 60;
	private int height = 22;
	private int length = 4;
	private int fontSize = 20;
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

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
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
