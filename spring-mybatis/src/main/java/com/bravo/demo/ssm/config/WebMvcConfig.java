package com.bravo.demo.ssm.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.util.WebUtils;

import com.bravo.demo.ssm.filter.TimerLoggerFilter;
import com.bravo.demo.ssm.interceptor.TimerLoggerInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	/*
	 * 其实注册 Filter 的config类不一定要继承自 WebMvcConfigurerAdapter
	 * 也可以直接给 Filter 类上加 @Component，即可启用该Filter，而不用这种配置方式。
	 */
	@Bean
	public FilterRegistrationBean timerLoggerFilter() {
		FilterRegistrationBean registBean = new FilterRegistrationBean();
		TimerLoggerFilter tLogFilter = new TimerLoggerFilter();
		registBean.setFilter(tLogFilter);
		
		List<String> urlPatterns = new ArrayList<>(1);
		urlPatterns.add("/users/*");
		registBean.setUrlPatterns(urlPatterns);
		
		return registBean;
	}

	@Autowired
	private TimerLoggerInterceptor timerLoggerInterceptor;
	
	/*
	 * 注册Interceptor的config类要继承自 WebMvcConfigurerAdapter
	 * 而且 Interceptor 类上别忘了加 @Component
	 * 也可使用 registry.addInterceptor(new TimerLoggerInterceptor) 的方式注册
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(timerLoggerInterceptor).addPathPatterns("/users/**"); //也可以使用 pathPattern对特定URL拦截
	}
	
	
	

//    
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("swagger-ui.html")
//				.addResourceLocations("classpath:/META-INF/resources/");
//
//	}	
	
	/**
	 * 这个 Bean 是为了支持自由切换语言。
	 * 即使没这个 Bean，一旦配置了spring.messages.basename， Spring Boot 也会根据当前的系统选择默认语言
	 */
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
}

class MyLocaleResolver implements LocaleResolver {
	private static final String LANGUAGE = "lang";
	private static final String LANGUAGE_SESSION = "lang_session";

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		// 如果前后端分离，每次请求默认还要带上这个参数
		String language = request.getParameter(LANGUAGE);
		if(StringUtils.isBlank(language)) {
			language = request.getHeader(LANGUAGE); // 如果从参数中取不到，再尝试从header中取
		}
		Locale locale = Locale.getDefault();
		
		if(!StringUtils.isEmpty(language)) {
			String[] strs = language.split("_");
			locale = new Locale(strs[0], strs[1]);
			
			//将国际化语言保存到session
			WebUtils.setSessionAttribute(request, LANGUAGE_SESSION, locale);
		} else {
			// 如没有，取之前保存的或者session里的配置
			Locale localeInSession = (Locale)WebUtils.getSessionAttribute(request, LANGUAGE_SESSION);
			if(localeInSession != null) {
				locale = localeInSession;
			}
		}
		
		return locale;
	}

	@Override
	public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
	}
}