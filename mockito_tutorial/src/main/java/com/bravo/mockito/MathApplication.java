package com.bravo.mockito;

import com.bravo.mockito.service.CalculatorService;

public class MathApplication {
	private CalculatorService calculatorService;

	public double add(double input1, double input2) {
		return calculatorService.add(input1, input2);
	}
	
	public double subtract(double input1, double input2) {
		return calculatorService.subtract(input1, input2);
	}
	
	public double multiply(double input1, double input2) {
		return calculatorService.multiply(input1, input2);
	}
	
	public double devide(double input1, double input2) {
		return calculatorService.devide(input1, input2);
	}
	
	public void setCalculatorService(CalculatorService calculatorService) {
		this.calculatorService = calculatorService;
	}
	
}
