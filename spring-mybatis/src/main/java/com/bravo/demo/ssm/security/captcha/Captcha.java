package com.bravo.demo.ssm.security.captcha;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class Captcha {
	private String code;
	private BufferedImage image;
	private LocalDateTime expireTime;
	
	public Captcha(String code, BufferedImage image, int expireInSeconds) {
		super();
		this.code = code;
		this.image = image;
		this.expireTime = LocalDateTime.now().plusSeconds(expireInSeconds);
	}
	
	public Captcha(String code, BufferedImage image, LocalDateTime expireTime) {
		super();
		this.code = code;
		this.image = image;
		this.expireTime = expireTime;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public LocalDateTime getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}
