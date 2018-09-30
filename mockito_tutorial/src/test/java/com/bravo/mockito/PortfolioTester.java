package com.bravo.mockito;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.bravo.mockito.service.StockService;

public class PortfolioTester {
	Portfolio portfolio;
	StockService stockService;
	
	public static void main(String[] args) {
		PortfolioTester tester = new PortfolioTester();
		tester.setUp();
		System.out.println(tester.testMarketValue() ? "Pass" : "Fail");
	}

	public void setUp() {
		portfolio = new Portfolio();
		stockService = mock(StockService.class);
		portfolio.setStockService(stockService);
	}
	
	public boolean testMarketValue() {
		List<Stock> stockList = new ArrayList<>();
		Stock googleStock = new Stock("1", "Google", 10);
		Stock msStock = new Stock("2", "Microsoft", 100);
		stockList.add(googleStock);
		stockList.add(msStock);
		
		portfolio.setStockList(stockList);
		
		when(stockService.getPrice(googleStock)).thenReturn(50.0);
		when(stockService.getPrice(msStock)).thenReturn(1000.0);
		
		double marketValue = portfolio.getMarketValue();
		System.out.println("marketValue = " + marketValue);
		return marketValue == 100500.0;
	}
}
