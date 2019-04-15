package com.bravo.demo.ssm.aspect;

import java.util.Arrays;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
 * 可以进行更细粒度的过滤和拦截处理，而且不仅仅只能用于 Web 相关的类或方法上。
 * 因此就无法知道初始的 request 请求信息
 */
@Aspect
@Component
@Order(2)
public class TimerLoggerAspect {

	private static Logger logger = LoggerFactory.getLogger(TimerLoggerAspect.class);
	
	@Pointcut("execution (* com.bravo.demo.ssm.controller.*Controller.*(..))")
	public void controllerJointPointExpression() {}

//	@Before("controllerJointPointExpression()")
//	public void beforeMethod(JoinPoint jp) {
//		logger.debug("Before method {}.", jp.getSignature().getName());
//	}
//	
//	// 无论方法是否抛异常都会执行
//	@After(value = "controllerJointPointExpression()")
//	public void afterMethod(JoinPoint jp) {
//		logger.debug("After method {}.", jp.getSignature().getName());
//	}
//	// 只有在方法正常结束后执行。返回通知可以访问方法的返回值
//	@AfterReturning(value = "controllerJointPointExpression()", returning = "result")
//	public void afterReturningMethod(JoinPoint jp, Object result) {
//		logger.debug("Method {} return with: {}", jp.getSignature().getName(), result);
//	}
	
	/*
	 * 必需要有 ProceedingJoinPoint 类型的参数。且必须要有返回值，即目标方法的返回值。
	 * 环绕通知类似于动态代理全过程。ProceedingJoinPoint 可以决定是否执行目标方法
	 */
	@Around("controllerJointPointExpression()")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) {
		logger.debug("Around method start ...");
		long start = new Date().getTime();
		Object[] args = pjp.getArgs();
		logger.debug("Around {}::{}::{}", pjp.getTarget().getClass().getName(), pjp.getSignature().getName(), Arrays.asList(args));
		Object result = null;
		try {
			// 这里相当于前置通知，即 @Before 方法
			result = pjp.proceed();
			// 这里相当于返回通知，即 @AfterReturning 方法
		} catch (Throwable e) {
			// 这里相当于异常通知，即 @AfterThrowing 方法
			e.printStackTrace();
		}
		// 这里相当于后置通知，即 @After 方法
		
		logger.debug("Around method ends in {} ms, and with result: {}.", new Date().getTime() - start, result);
		
		return result;
	}

//	// 可以在抛出指定异常时执行。如抛出 NullPointerException 时
//	@AfterThrowing(value = "controllerJointPointExpression()", throwing = "e")
//	public void afterThrowingMethod(JoinPoint jp, Throwable e /*NullPointerException e*/) {
//		String methodName = jp.getSignature().getName();
//		logger.error("Method {} occurs exception: {}", methodName, e);
//	}
}
