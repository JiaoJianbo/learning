package com.bravo.demo.ssm.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.util.WebUtils;

public class MyLocaleResolver implements LocaleResolver {
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