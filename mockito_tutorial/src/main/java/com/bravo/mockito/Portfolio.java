package com.bravo.mockito;

import java.util.List;

import com.bravo.mockito.service.StockService;

public class Portfolio {
	private StockService stockService;
	private List<Stock> stockList;
	
	public StockService getStockService() {
		return stockService;
	}
	public void setStockService(StockService stockService) {
		this.stockService = stockService;
	}
	public List<Stock> getStockList() {
		return stockList;
	}
	public void setStockList(List<Stock> stockList) {
		this.stockList = stockList;
	}
	
	public double getMarketValue() {
		double marketValue = 0.0;
		
		for(Stock stock : stockList) {
			marketValue += stockService.getPrice(stock) * stock.getQuantity();
		}
		
		return marketValue;
	}
	
}
