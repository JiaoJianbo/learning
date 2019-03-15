package com.bravo.demo.servlet3.listener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class MyAsyncListener implements AsyncListener {

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
		printMsg(event, "onComplete");
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
		printMsg(event, "onTimeout");
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
		printMsg(event, "onError");
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
		printMsg(event, "onStartAsync");
	}

	private void printMsg(AsyncEvent event, String msg) throws IOException {
		System.out.println(msg);
		AsyncContext ctx = event.getAsyncContext();
		PrintWriter out = ctx.getResponse().getWriter();
		out.println("<h5><font color=\"#FF0000\">" + msg + "</font></h5>");
		out.flush();
	}
}
