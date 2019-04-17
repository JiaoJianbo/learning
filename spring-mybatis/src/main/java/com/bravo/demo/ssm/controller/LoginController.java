package com.bravo.demo.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bravo.demo.ssm.security.SecurityProperties;

@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;

	// 根据用户的请求地址，判断该定向到登录页还是返回状态码
//	@RequestMapping("/tologin")
//	public ResponseEntity<String> toLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		SavedRequest savedRequest = requestCache.getRequest(request, response);
//		if(savedRequest != null) {
//			String targetUrl = savedRequest.getRedirectUrl();
//			logger.debug("引发跳转的URL: {}", targetUrl);
//			
//			// 如果之前访问的是html，那么跳到登录页。否则返回 401 状态码
//			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
//				redirectStrategy.sendRedirect(request, response, securityProperties.getLoginUrl()); //loginUrl 是可配置的
//			}
//		}
//		
//		ResponseEntity<String> result = new ResponseEntity<>("User havn't login. Login please.", HttpStatus.UNAUTHORIZED);
//		return result; 
//	}

	//在目前应用中统一跳转到登录页
	@RequestMapping("/tologin")
	public String toLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.debug("引发跳转的URL: {}", targetUrl);
		}
//		//在目前应用中统一跳转到登录页
//		redirectStrategy.sendRedirect(request, response, securityProperties.getLoginUrl()); 
		
		return "login"; //不要 .html 后缀
	}

}
