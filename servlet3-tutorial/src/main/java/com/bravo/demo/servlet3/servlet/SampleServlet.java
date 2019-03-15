package com.bravo.demo.servlet3.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/sample"}, asyncSupported = true, loadOnStartup = -1,
name = "sampleServlet", displayName = "dis_samp_serv",
initParams= {@WebInitParam(name = "username", value = "JamesBond"), 
		@WebInitParam(name = "password", value = "JB@007")}
)

public class SampleServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5628603636573005455L;

	@Override
	public void init() throws ServletException {
		String name = super.getInitParameter("username");
		String pwd = super.getInitParameter("password");
		System.out.println("Init Parameters: " + name + " --- " + pwd);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Process request");
		PrintWriter out = resp.getWriter();
		out.println("SampleServlet...");
		out.flush();
	}

}
