package com.bravo.demo.ssm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskService {
	private static Logger log = LoggerFactory.getLogger(ScheduledTaskService.class);

	/*
	 * cron-like expression, format like:
	 * second, minute, hour, day of month, month, day of week.
	 * e.g. "0 * * * * MON-FRI"
	 */
	//@Scheduled(cron = "0 */3 * * * *") // 每3分钟执行一次
	@Scheduled(initialDelay = 5000, fixedRate = 3* 60 * 1000L) // 启动后延迟 5 秒执行，然后每3分钟执行一次
	public void task1() {
		log.debug("heart beated ......");
	}
}
