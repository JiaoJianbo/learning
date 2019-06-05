package com.bravo.demo.ssm.security.session;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.bravo.demo.ssm.security.LoginType;

// 参考 {@link org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy}
public class MySessionInformationExpiredStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

	public MySessionInformationExpiredStrategy(String invalidSessionUrl, LoginType loginType) {
		super(invalidSessionUrl, loginType);
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		logger.debug("MySessionInformationExpiredStrategy", event);
		this.onSessionInvalid(event.getRequest(), event.getResponse());
	}

	@Override
	protected boolean isConcurrency() {
		return true;
	}
}
