/**
 * 
 */
package com.bravo.interview.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Bobby
 *
 */
public class ArithmeticServiceLoggingProxy { //不用实现接口
	// 被代理的目标对象
	private ArithmeticService target;
	
	public ArithmeticServiceLoggingProxy(ArithmeticService target) {
		super();
		this.target = target;
	}

	public ArithmeticService getLoggingProxy() {
		//代理对象由哪一个 ClassLoader 负责加载
		ClassLoader loader = target.getClass().getClassLoader();
		
		//代理对象的类型，实现了哪些接口
		Class<?>[] interfaces = new Class[] {ArithmeticService.class};
		
		//当调用代理对象的方法时，要执行的代码
		/*
		 * 主要逻辑其实都在 InvocationHandler 里面，如果可以单出抽出去，那么这里的逻辑可以应用到任何 target 上
		 * 参见 LoggingInterceptor
		 */
		InvocationHandler h = new InvocationHandler() {
			/**
			 * proxy : 正在返回的那个对象，一般情况下，在 invoke 方法中不使用该对象
			 * method: 正在被调用的方法
			 * args  : 调用方法时，传入的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//System.out.println(proxy.toString()); //java.lang.StackOverflowError
				String methodName = method.getName();
				// 前面日志
				System.out.println("Method " + methodName + " begins with " + Arrays.asList(args));
				Object result = method.invoke(target, args);
				// 后面日志
				System.out.println("Method " + methodName + " ends with: " + result);
				return result;
			}
		};

		ArithmeticService proxy = (ArithmeticService) Proxy.newProxyInstance(loader, interfaces, h);
		
		return proxy;
	}
	
}
