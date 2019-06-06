package com.bravo.demo.ssm.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {
	private static Logger log = LoggerFactory.getLogger(AsyncTaskService.class);

	@Async
	public void task1() {
		try {
			TimeUnit.MILLISECONDS.sleep(5000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.debug("This is a Async task demo ......");
	}
}
