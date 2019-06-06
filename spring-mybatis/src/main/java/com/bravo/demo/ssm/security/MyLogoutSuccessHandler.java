package com.bravo.demo.ssm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * See {@link org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler} base class logic.
 * 
 * @author Bobby
 *
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
	private static Logger logger = LoggerFactory.getLogger(MyLogoutSuccessHandler.class);
	
	private String logoutUrl;
	
	private LoginType loginType;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public MyLogoutSuccessHandler(String logoutUrl, LoginType loginType) {
		this.logoutUrl = logoutUrl;
		this.loginType = loginType;
	}

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {
		if(authentication != null) {
			logger.debug("{} logout successfully.", ((UserDetails)authentication.getPrincipal()).getUsername());
		} else {
			logger.debug("MyLogoutSuccessHandler...");
		}
		
		if(loginType == LoginType.JSON) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.OK.value());
			response.getWriter().write("成功退出");
		} else {
			logger.debug("redirect to: {}", logoutUrl);
			//response.sendRedirect(logoutUrl);
			redirectStrategy.sendRedirect(request, response, logoutUrl);
		}
	}
	
}
