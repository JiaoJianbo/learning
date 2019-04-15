package com.bravo.demo.ssm.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Filter 无法获取所过滤的对象方法名和方法参数等信息。要这些信息那么要使用Interceptor和Aspect
//@Component
public class TimerLoggerFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(TimerLoggerFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("TimerLoggerFilter init.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		long start = new Date().getTime();
		logger.debug("TimerLoggerFilter start ... ");
		chain.doFilter(request, response);
		logger.debug("TimerLoggerFilter finished in {} ms.", (new Date().getTime() - start));
	}

	@Override
	public void destroy() {
		logger.info("TimerLoggerFilter destroy.");
	}

}
