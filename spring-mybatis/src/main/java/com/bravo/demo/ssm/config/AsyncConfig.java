package com.bravo.demo.ssm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*
 * 初始化线程池，用于执行异步任务（Async task）
 * 当没有这个配置时，Spring 也会提供默认的线程池实现
 * 
 * See {@link org.springframework.scheduling.annotation.AnnotationAsyncExecutionInterceptor}
 *     {@link org.springframework.aop.interceptor.AsyncExecutionAspectSupport}
 */
@Configuration
public class AsyncConfig {
	// 以下参数值应该使用 @Value 从配置中读取
	private int corePoolSize = 5;
	private int maxPoolSize = 10;
	private int keepAliveSeconds = 60;
	private int queueCapacity = 50;
	
	/*
	 * 参考 java.util.concurrent.ThreadPoolExecutor, 有七大参数
	 * 默认参数中有些坑，最好自己手动设置参数。
	 * 如，默认的等待队列容量是  Integer.MAX_VALUE
	 */
	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize); //默认值是 Integer.MAX_VALUE
		executor.setQueueCapacity(queueCapacity); //默认值是 Integer.MAX_VALUE
		executor.setKeepAliveSeconds(keepAliveSeconds);
		
		return executor;
	}
}
