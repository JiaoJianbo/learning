/**
 * 
 */
package com.bravo.interview.core.proxy;

/**
 * @author Bobby
 *
 */
public class ArithmeticServiceImpl implements ArithmeticService {

	@Override
	public int add(int a, int b) {
		int result = a + b;
		System.out.println("add --> " + result);
		return result;
	}

	@Override
	public int sub(int a, int b) {
		int result = a - b;
		System.out.println("sub --> " + result);
		return result;
	}

	@Override
	public int mul(int a, int b) {
		int result = a * b;
		System.out.println("div --> " + result);
		return result;
	}

	@Override
	public int div(int a, int b) {
		int result = a / b;
		System.out.println("mul --> " + result);
		return result;
	}

}
