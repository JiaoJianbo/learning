/**
 * 
 */
package com.bravo.interview.core.proxy;

/**
 * @author Bobby
 *
 */
public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArithmeticService arithmeticService = new ArithmeticServiceImpl();
		arithmeticService.add(1, 2);
		arithmeticService.mul(3, 4);

		System.out.println("---------------------------------");
//		ArithmeticService arithmeticLoggingService = new ArithmeticServiceLoggingImpl();
//		arithmeticLoggingService.add(1, 2);
//		arithmeticLoggingService.mul(3, 4);
		
		ArithmeticServiceLoggingProxy proxy = new ArithmeticServiceLoggingProxy(arithmeticService);
		ArithmeticService loggingProxy = proxy.getLoggingProxy();
		System.out.println(loggingProxy.getClass().getName()); // com.sun.proxy.$Proxy0
		loggingProxy.add(1, 2);
		loggingProxy.mul(3, 4);
	}

}
