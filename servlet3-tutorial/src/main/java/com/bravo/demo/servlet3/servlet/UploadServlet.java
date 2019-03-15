package com.bravo.demo.servlet3.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(urlPatterns = "/upload", asyncSupported = true)
//表示该 Servlet 希望处理的请求的 MIME 类型是 multipart/form-data
@MultipartConfig(location = "/uploadFiles", maxFileSize = 5242880, maxRequestSize = 10485760) //单位 Byte
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -4492498300446253282L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");		
		
		try{
			//存储路径
			//String savePath = req.getServletContext().getRealPath("/WEB-INF/uploadFile");
			
			Part part = req.getPart("upfile"); // 页面 form 表单中，file 域的名称
			
			//获取请求头，请求头的格式：form-data; name="file"; filename="snmp4j--api.zip"
			String header = part.getHeader("content-disposition");
			//获取文件名
			String fileName = getFileName(header);
			/*
			 * 如果使用 tomcat maven plugin:
			 *   上传文件位于 target/tomcat/work/Tomcat/localhost/servlet3-tutorial/uploadFiles， 而且路徑必須先存在
			 *   
			 * 如果使用 jetty maven plugin:
			 *   上传文件位于 target/tmp/uploadFiles，此路径会自动生成
			 */
			part.write(File.separator + fileName); //基於location的位置，必須是已存在目錄

			out.println("<font color=\"green\">Upload completed!</font>");
		} catch(Exception e){
			out.println("<font color='red'>Upload error</font>, " + e.getMessage());
			e.printStackTrace();
		}
		
		out.flush();
	}

	
	/**
     * 根据请求头解析出文件名
     * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="snmp4j--api.zip"
     *                 IE浏览器下：form-data; name="file"; filename="E:\snmp4j--api.zip"
     * @param header 请求头
     * @return 文件名
     */
	public String getFileName(String header) {
	    /**
	     * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
	     * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
	     * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
	     */
	    String[] tempArr1 = header.split(";");
	    /**
	     *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
	     *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
	     */
	    String[] tempArr2 = tempArr1[2].split("=");
	    //获取文件名，兼容各种浏览器的写法
	    String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\")+1).replaceAll("\"", "");
	    return fileName;
	}
}
