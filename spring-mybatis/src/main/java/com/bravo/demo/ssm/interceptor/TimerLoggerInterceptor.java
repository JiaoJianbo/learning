package com.bravo.demo.ssm.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/*
 * 不仅会拦截自己写的Controller，连Spring 自带的 Controller也会被拦截
 * Interceptor较Filter来说，可以得到调用的类和方法名，但是仍不能得到方法的参数值
 * 要获得方法执行时的参数，请使用Aspect
 */
@Component
public class TimerLoggerInterceptor implements HandlerInterceptor {
	private static Logger logger = LoggerFactory.getLogger(TimerLoggerInterceptor.class);

	/*
	 * 在请求处理之前进行调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod mh = (HandlerMethod)handler;
		logger.debug("TimerLoggerInterceptor.preHandle - {}::{} start ...", mh.getBean().getClass().getName(), mh.getMethod().getName());
		request.setAttribute("startTime", new Date().getTime());
		return true; // 是否执行后续的方法
	}

	/*
	 * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）。
	 * 如果Controller的方法抛出异常，则不会被调用
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		Long start = (Long)request.getAttribute("startTime");
		logger.debug("TimerLoggerInterceptor.postHandle - in {} ms.", new Date().getTime() - start);
	}
	
	/*
	 * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	 * 即使 Controller 的方法抛出异常，该方法仍然会被调用. 如果没抛异常那么 ex 是 null。
	 * 但是要特别注意：如果抛出了异常，但是被ControllerAdvice处理了的话，这里仍然是null。
	 * 注意过滤的顺序：Request -> Filter -> Interceptor -> ControllerAdvice -> Aspect -> Controller -> |
	 *               Response <- Filter <- Interceptor <- ControllerAdvice <- Aspect <- Controller <- |
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		Long start = (Long)request.getAttribute("startTime");
		logger.debug("TimerLoggerInterceptor.afterCompletion - in {} ms.", new Date().getTime() - start);
	}

}
