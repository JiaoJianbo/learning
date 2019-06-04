package com.bravo.demo.ssm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * 我们自己不提供 AuthenticationSuccessHandler 时，SpringSecurity为我们提供一个默认实现，
 * 即 SavedRequestAwareAuthenticationSuccessHandler。
 */
@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
	
	@Autowired //系统启动时已经自动注入了一个 ObjectMapper
	private ObjectMapper objectMapper;
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.debug("Authentication === {}", objectMapper.writeValueAsString(authentication));
		
		if(securityProperties.getLoginType() == LoginType.JSON) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.OK.value());
			response.getWriter().write(objectMapper.writeValueAsString(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
