package com.bravo.demo.ssm.security.captcha;

import org.springframework.security.core.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184420168168904169L;

	public CaptchaException(String msg) {
		super(msg);
	}

	public CaptchaException(String msg, Throwable t) {
		super(msg, t);
	}

}
