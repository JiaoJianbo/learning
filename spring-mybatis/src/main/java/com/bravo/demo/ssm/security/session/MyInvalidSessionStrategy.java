package com.bravo.demo.ssm.security.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;

import com.bravo.demo.ssm.security.LoginType;

// 参考{@link org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy}
public class MyInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public MyInvalidSessionStrategy(String invalidSessionUrl, LoginType loginType) {
		super(invalidSessionUrl, loginType);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.debug("MyInvalidSessionStrategy ... ");
		this.onSessionInvalid(request, response);
	}

}
