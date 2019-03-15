package com.bravo.demo.servlet3.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bravo.demo.servlet3.listener.MyAsyncListener;

@WebServlet(urlPatterns = "/asyndemo", asyncSupported = true)
public class AsynDemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6038606716139503351L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("<h5>Enter Servlet time : " + new Date() + ". </h5>");
		out.flush();
		
		//在子线程中执行业务调用，并由其负责输出响应，主线程退出
		AsyncContext ctx = req.startAsync();
		ctx.addListener(new MyAsyncListener());
		new Thread(new Executor(ctx)).start();
		
		out.println("<h5>Exit Servlet time : " + new Date() + ". </h5>");
		out.flush();
	}

}