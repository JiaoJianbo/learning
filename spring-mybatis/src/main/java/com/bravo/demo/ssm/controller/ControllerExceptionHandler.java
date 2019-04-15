package com.bravo.demo.ssm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ControllerAdvice
public class ControllerExceptionHandler {
	
//	@ExceptionHandler(RuntimeException.class/* MyException.class */)
//	@ResponseBody
//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	public Map<String, Object> handleMyException(/* MyException e */){
//		Map<String, Object> result = new HashMap<>();
//		
//		// TODO
//		
//		return result;
//	}
//	
//	
//	protected HttpStatus getStatus(HttpServletRequest request) {
//		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//		if (statusCode == null) {
//			return HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//		try {
//			return HttpStatus.valueOf(statusCode);
//		}
//		catch (Exception ex) {
//			return HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//	}


}
