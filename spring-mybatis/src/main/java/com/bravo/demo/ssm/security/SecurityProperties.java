package com.bravo.demo.ssm.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.bravo.demo.ssm.security.captcha.CaptchaProperties;

@ConfigurationProperties(prefix="bravo.security")
public class SecurityProperties {
	
	protected static final String SESSION_INVALID_URL = "/tologin?invalidSession";
	
	protected static final String SESSION_EXPIRED_URL = "/tologin?expiredSession";

	private String loginUrl = "/login.html";
	
	private LoginType loginType = LoginType.REDIRECT;
	
	private int rememberMeSeconds = 3600;
	
	private CaptchaProperties captcha = new CaptchaProperties();
	
	/** Session 超时跳转地址 */
	private String invalidSessionUrl = SESSION_INVALID_URL;
	
	/** Session 失效（被踢掉）后跳转地址 */
	private String expiredSessionUrl = SESSION_EXPIRED_URL;
	
	private String logoutUrl = "/tologin?logout";
	
	private Integer maxSessions = 1;
	
	private boolean maxSessionsPreventsLogin = false;
	
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

	public String getInvalidSessionUrl() {
		return invalidSessionUrl;
	}

	public void setInvalidSessionUrl(String invalidSessionUrl) {
		this.invalidSessionUrl = invalidSessionUrl;
	}

	public Integer getMaxSessions() {
		return maxSessions;
	}

	public void setMaxSessions(Integer maxSessions) {
		this.maxSessions = maxSessions;
	}

	public String getLogoutUrl() {
		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	public String getExpiredSessionUrl() {
		return expiredSessionUrl;
	}

	public void setExpiredSessionUrl(String expiredSessionUrl) {
		this.expiredSessionUrl = expiredSessionUrl;
	}

	public boolean isMaxSessionsPreventsLogin() {
		return maxSessionsPreventsLogin;
	}

	public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
		this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
	}
	
}
