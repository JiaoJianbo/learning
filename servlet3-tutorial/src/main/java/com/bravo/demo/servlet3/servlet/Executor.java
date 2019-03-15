package com.bravo.demo.servlet3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;

// 模拟异步处理业务
public class Executor implements Runnable {
	private AsyncContext ctx = null;

	public Executor(AsyncContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public void run() {
		try {
			// 等待5秒钟，以模拟业务方法的执行
			Thread.sleep(5*1000);
			PrintWriter out = ctx.getResponse().getWriter();
			out.println("Business completed at : " + new Date());
			out.flush();
			ctx.complete();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
