/**
 * 
 */
package com.bravo.interview.core.proxy;

/**
 * @author Bobby
 *
 * 常规做法——在每个方法前后打印日志。
 */
public class ArithmeticServiceLoggingImpl implements ArithmeticService {

	@Override
	public int add(int a, int b) {
		System.out.println("Method add begins with (" + a + ", " + b + ")");
		int result = a + b;
		System.out.println("add --> " + result);
		System.out.println("Method add ends with result: " + result);
		return result;
	}

	@Override
	public int sub(int a, int b) {
		System.out.println("Method sub begins with (" + a + ", " + b + ")");
		int result = a - b;
		System.out.println("sub --> " + result);
		System.out.println("Method sub ends with result: " + result);
		return result;
	}

	@Override
	public int mul(int a, int b) {
		System.out.println("Method mul begins with (" + a + ", " + b + ")");
		int result = a * b;
		System.out.println("mul --> " + result);
		System.out.println("Method mul ends with result: " + result);
		return result;
	}

	@Override
	public int div(int a, int b) {
		System.out.println("Method div begins with (" + a + ", " + b + ")");
		int result = a / b;
		System.out.println("div --> " + result);
		System.out.println("Method div ends with result: " + result);
		return result;
	}

}
