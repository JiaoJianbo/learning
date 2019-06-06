package com.bravo.demo.ssm.security.captcha;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import com.bravo.demo.ssm.security.SecurityProperties;

/*
 * 自定义的校验图片验证码的过滤器，最后将该过滤器加入到 Spring Security的过滤器链中
 */
public class CaptchaFilter extends OncePerRequestFilter/* implements InitializingBean */ {
	private static Logger logger = LoggerFactory.getLogger(CaptchaFilter.class);
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	// 需要进行验证码校验的请求地址，目前只有登录验证
	private Set<String> targetUrls = new HashSet<>();

	private SecurityProperties securityProperties;
	
	// 加入错误消息国际化功能
//	private MessageSource messageSource;
//	private MyLocaleResolver myLocaleResolver;
	// 使用  Spring Security 自己的国际化 
	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	
	private AntPathMatcher antPathMatcher = new AntPathMatcher();
	
	/* OncePerRequestFilter 的父类 GenericFilterBean 已经实现了InitializingBean 接口
	 * 
	 * @see org.springframework.web.filter.GenericFilterBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		//Set<String> configUrls = org.springframework.util.StringUtils.commaDelimitedListToSet(securityProperties.getCaptcha().getTargetUrl());
		//this.targetUrls.addAll(configUrls);
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCaptcha().getTargetUrl(), ",");
		for (String configUrl : configUrls) {
			this.targetUrls.add(StringUtils.trim(configUrl));
		}
		targetUrls.add("/authentication/form"); // loginProcessingUrl
		logger.debug("afterPropertiesSet");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		boolean perform = false;
		
		for (String pattern : targetUrls) {
			if(antPathMatcher.match(pattern, request.getRequestURI())) {
				perform = true;
			}
		}
		
		if(perform) {
			try {
				validate(request);
			} catch (CaptchaException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

	private void validate(HttpServletRequest request) throws ServletRequestBindingException {
		String captchaCode = (String) WebUtils.getSessionAttribute(request, CaptchaController.SESSION_CAPTCHA_KEY);
		LocalDateTime captcharExpiredTime = (LocalDateTime) WebUtils.getSessionAttribute(request, CaptchaController.SESSION_CAPTCHA_EXPIRED_TIME);
		
		String codeInRequest = ServletRequestUtils.getStringParameter(request, "captcha");
		//Locale locale = LocaleContextHolder.getLocale();
		//Locale locale = myLocaleResolver.resolveLocale(request);
		
		if(StringUtils.isBlank(codeInRequest)) {
			throw new CaptchaException("验证码不能为空");
		}
		
		if(captchaCode == null) {
			throw new CaptchaException("验证码不存在");
		}
		
		//if(captcha.isExpried()) {
		if(LocalDateTime.now().isAfter(captcharExpiredTime)) {
			WebUtils.setSessionAttribute(request, CaptchaController.SESSION_CAPTCHA_KEY, null); //remove attribute
			WebUtils.setSessionAttribute(request, CaptchaController.SESSION_CAPTCHA_EXPIRED_TIME, null); //remove attribute
			throw new CaptchaException("验证码已过期");
		}

		if(!StringUtils.equalsIgnoreCase(codeInRequest, captchaCode)) {
			//throw new CaptchaException("验证码不匹配");
			//throw new CaptchaException(messageSource.getMessage("captcha.notmatch", null, locale));
			throw new CaptchaException(messages.getMessage("captcha.notmatch", "验证码不匹配!"));
		}
		
		WebUtils.setSessionAttribute(request, CaptchaController.SESSION_CAPTCHA_KEY, null); //remove attribute
		WebUtils.setSessionAttribute(request, CaptchaController.SESSION_CAPTCHA_EXPIRED_TIME, null); //remove attribute
	}
	
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}

}
