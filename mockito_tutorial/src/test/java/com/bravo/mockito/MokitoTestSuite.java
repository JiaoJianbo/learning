package com.bravo.mockito;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MokitoTestSuite {

	// 验证行为是否发生
	@Test
	public void verifyBehaviour() {
		// 模拟创建一个List对象
		List mockList = mock(List.class);
		
		// 使用mock的对象
		mockList.add(1);
		mockList.clear();
		// 验证add(1)和clear()行为是否发生
		verify(mockList).add(1);
		verify(mockList).clear();
	}
	
	/*
	 * 模拟所期望的结果
	 * 
	 * 想让返回什么结果就 mock 让 return 什么结果，而不用管实际的结果
	 */
	@Test
	public void testWhenReturn() {
        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        //验证结果
        assertEquals("hello world world", result);
		// ---------------------------------------------
        Map map = mock(Map.class);
        when(map.put("a", "b")).thenReturn("Hello").thenReturn("World");
        String s = map.put("a", "b") + " " + map.put("a", "b") + " " + map.put("a", "b");
        assertEquals("Hello World World", s);
	}
	
	/*
	 * 在创建mock对象时，有的方法我们没有进行stubbing，所以调用时会放回Null这样在进行操作是很可能抛出NullPointerException。
	 * 如果通过RETURNS_SMART_NULLS参数创建的mock对象在没有调用stubbed方法时会返回SmartNull。
	 * 
	 * 例如：返回类型是String，会返回""。是int，会返回0；是List，会返回空的List。
	 */
	@Test
	public void testReturnsSmartNulls() {
		List list = mock(List.class, RETURNS_SMART_NULLS); // RETURNS_SMART_NULLS实现了Answer接口的对象
		// 使用RETURNS_SMART_NULLS参数创建的mock对象，不会抛出NullPointerException异常。
		// 另外控制台窗口会提示信息“SmartNull returned by this unstubbed method call on a mock:”
		System.out.println(list.get(0));
		
		System.out.println(list.toArray().length);
	}
	
	/*
	 * RETURNS_DEEP_STUBS参数程序会自动进行mock所需的对象。
	 * 如果不使用 RETURNS_DEEP_STUBS 参数，则抛出异常：
	 *     java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
	 * 
	 * 还有一种方法就是使用反射，为 mock 出来的 portfolio 对象设置必需的属性。Spring 中有工具类 ReflectionTestUtils。
	 */
	@Test
	public void testReturnsDeepStubs() {
		Portfolio portfolio = mock(Portfolio.class, RETURNS_DEEP_STUBS);
		when(portfolio.getStockList().get(0).getName()).thenReturn("Apple Inc.");
		portfolio.getStockList().get(0).getName();
		verify(portfolio.getStockList().get(0)).getName();
		assertEquals(portfolio.getStockList().get(0).getName(), "Apple Inc.");
	}
	// 同上
	@Test
	public void testReturnsDeepStubs2() {
		Portfolio portfolio = mock(Portfolio.class);
		List<Stock> stockList = mock(List.class);
		Stock stock = mock(Stock.class);
		when(portfolio.getStockList()).thenReturn(stockList);
		when(portfolio.getStockList().get(0)).thenReturn(stock);
		when(stock.getName()).thenReturn("Apple Inc.");
		
		portfolio.getStockList().get(0).getName();
		verify(portfolio.getStockList().get(0)).getName();
		assertEquals(portfolio.getStockList().get(0).getName(), "Apple Inc.");
	}
	
	// 模拟抛出指定异常
	@Test(expected = RuntimeException.class)
	public void testThrowWhen() {
		List<Integer> list = mock(List.class); 
		doThrow(new RuntimeException()).when(list).add(1);
		
		//list.add(2); //不会抛异常
		list.add(1); //才会抛异常
	}
	
	
}
