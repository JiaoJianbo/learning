package com.bravo.mockito;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.BDDMockito.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.bravo.mockito.service.CalculatorService;

//@RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTester {
	//@InjectMocks annotation is used to create and inject the mock object
	@InjectMocks
	MathApplication mathApplication = new MathApplication();
	
	//@Mock annotation is used to create the mock object to be injected
	@Mock
	CalculatorService calculatorService;
	
	//Or init the beans as following
//	private MathApplication mathApplication;
//	private CalculatorService calculatorService;
//	@Before
//	public void setUp() {
//		mathApplication = new MathApplication();
//		calculatorService = mock(CalculatorService.class);
//		mathApplication.setCalculatorService(calculatorService);
//	}
	
	@Test
	public void testAdd() {
		//add the behavior of calc service to add two numbers
		when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);
		
		//test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
	}
	
	@Test
	public void testVerify() {
		//add the behavior of calc service to add two numbers
		when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);
		
		//add the behavior of calc service to subtract two numbers
		when(calculatorService.subtract(30.0, 10.0)).thenReturn(20.0);
		
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		
		//test the subtract functionality
		Assert.assertEquals(mathApplication.subtract(30.0, 10.0), 20.0, 0.0);
		
		//verify the behavior
		//verify(calculatorService).add(30.0, 20.0);
		
		//check if add function is called three times
		verify(calculatorService, times(3)).add(10.0, 20.0);
		
		//default call count is 1
		verify(calculatorService).subtract(30.0, 10.0);
		
		//verify that method was never called on a mock
		verify(calculatorService, never()).multiply(30.0, 10.0);
	}
	
	@Test
	public void testVaryingCalls() {
		//add the behavior of calc service to add two numbers
		when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);
		
		//add the behavior of calc service to subtract two numbers
		when(calculatorService.subtract(30.0, 10.0)).thenReturn(20.0);
		
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		
		//test the subtract functionality
		Assert.assertEquals(mathApplication.subtract(30.0, 10.0), 20.0, 0.0);
		
		//check a minimum 1 call count
		verify(calculatorService, atLeastOnce()).subtract(30.0, 10.0);
		
		//check if add function is called minimum 2 times
		verify(calculatorService, atLeast(2)).add(10.0, 20.0);
		
		//check if add function is called maximum 3 times
		verify(calculatorService, atMost(3)).add(10.0, 20.0);
	}
	
	@Test (expected = RuntimeException.class)
	public void testExceptionHandling() {
		//add the behavior to throw exception
		doThrow(new RuntimeException("Add operation have not implemented.")).when(calculatorService).add(10.0, 20.0);
		
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
	}
	
	@Test
	public void testOrderVerification() {
		//add the behavior of calc service to add two numbers
		when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);
		
		//add the behavior of calc service to subtract two numbers
		when(calculatorService.subtract(30.0, 10.0)).thenReturn(20.0);
		
		//test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		
		//test the subtract functionality
		Assert.assertEquals(mathApplication.subtract(30.0, 10.0), 20.0, 0.0);
		
		//create an inOrder verifier for a single mock
		InOrder inOrder = inOrder(calculatorService);
		
		//following will make sure that add is first called then subtract is called.
		inOrder.verify(calculatorService).add(10.0, 20.0);
		inOrder.verify(calculatorService).subtract(30.0, 10.0);
	}
	
	@Test
	public void testCallbacks() {
		//add the behavior to add numbers
		when(calculatorService.add(10.0, 20.0)).thenAnswer(new Answer<Double>() {

			@Override
			public Double answer(InvocationOnMock invocation) throws Throwable {
				//get the arguments passed to mock
				Object[] args = invocation.getArguments();
				System.out.println(args[0]);
				
				//get the mock
				Object mock = invocation.getMock();
				System.out.println(mock);
				
				return 30.0;
			}
		});
	
		//test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
	}
	
	
	// Mockito provides option to create spy on real objects. 
	// When spy is called, then actual method of real object is called.
	@Test
	public void testSpying() {
		mathApplication = new MathApplication();
		Calculator calculator = new Calculator();
		calculatorService = spy(calculator);
		mathApplication.setCalculatorService(calculatorService);
		
		//perform operation on real object
		//test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
	}
	
	class Calculator implements CalculatorService {
		@Override
		public double add(double input1, double input2) {
			return input1 + input2;
		}

		@Override
		public double subtract(double input1, double input2) {
			throw new UnsupportedOperationException("Method not implemented yet!");
		}

		@Override
		public double multiply(double input1, double input2) {
			throw new UnsupportedOperationException("Method not implemented yet!");
		}

		@Override
		public double devide(double input1, double input2) {
			throw new UnsupportedOperationException("Method not implemented yet!");
		}
	}
	
	//Mockito provides the capability to a reset a mock so that it can be reused later. 
	@Test
	public void testRest() {
		//add the behavior of calc service to add two numbers
		when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);
		
		//test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		
		//reset the mock
		reset(calculatorService);
		
		//test the add functionality after resetting the mock
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
	}
	
	//Behavior Driven Development is a style of writing tests uses given, when and then format as test methods. 
	@Test
	public void testBDD() {
		//Given
		given(calculatorService.add(10.0, 20.0)).willReturn(30.0);
		
		//When
		double result = calculatorService.add(10.0, 20.0);
		
		//Then
		Assert.assertEquals(result, 30.0, 0.0);
	}
	
	//Mockito provides a special Timeout option to test if a method is called within stipulated time frame.
	@Test
	public void testTimeout() {
		//add the behavior of calc service to add two numbers
		when(calculatorService.add(10.0, 20.0)).thenReturn(30.0);
		
		//add the behavior of calc service to subtract two numbers
		when(calculatorService.subtract(30.0, 10.0)).thenReturn(20.0);
		
		//test the subtract functionality
		Assert.assertEquals(mathApplication.subtract(30.0, 10.0), 20.0, 0.0);
		
		//test the add functionality
		Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0.0);
		
		//verify call to add method to be completed within 100 ms
		verify(calculatorService, timeout(10)).add(10.0, 20.0);
		
		//invocation count can be added to ensure multiplication invocations
		//can be checked within given timeframe
		verify(calculatorService, timeout(10).times(1)).add(10.0, 20.0);
	}
}
