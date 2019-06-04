package com.bravo.demo.ssm.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("myAuthenticationFailureHandler")
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Autowired //系统启动时已经自动注入了一个 ObjectMapper
	private ObjectMapper objectMapper;
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		if(securityProperties.getLoginType() == LoginType.JSON) {
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.getWriter().write(objectMapper.writeValueAsString(exception.getLocalizedMessage()));
		}else {
			// 如果不设置，会跳到默认的 Whitelabel Error Page
			super.setDefaultFailureUrl("/tologin?error");
			super.onAuthenticationFailure(request, response, exception);
		}
	}

}
