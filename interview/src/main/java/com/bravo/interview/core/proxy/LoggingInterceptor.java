package com.bravo.interview.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 单独抽出日功能，这样该功能就可以被应用到任何被代理的对象上
 * 
 * @author Bobby
 *
 */
public class LoggingInterceptor implements InvocationHandler {
	private Object target;

	public LoggingInterceptor(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		String methodName = method.getName();
		// 前面日志
		System.out.println("Method " + methodName + " begins with " + Arrays.asList(args));
		
		Object result = method.invoke(target, args);

		// 后面日志
		System.out.println("Method " + methodName + " ends with: " + result);
		return result;
	}

}
