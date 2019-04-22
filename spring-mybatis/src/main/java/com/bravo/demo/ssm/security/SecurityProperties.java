package com.bravo.demo.ssm.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.bravo.demo.ssm.security.captcha.CaptchaProperties;

@ConfigurationProperties(prefix="bravo.security")
public class SecurityProperties {

	private String loginUrl = "/login.html";
	
	private LoginType loginType = LoginType.REDIRECT;
	
	private int rememberMeSeconds = 3600;
	
	private CaptchaProperties captcha = new CaptchaProperties();

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public CaptchaProperties getCaptcha() {
		return captcha;
	}

	public void setCaptcha(CaptchaProperties captcha) {
		this.captcha = captcha;
	}
	
}
