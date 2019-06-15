package com.bravo.demo.ssm.amqp;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAMQPConfig {

	/*
	 * 重写 RabbitTemplate 默认的 messageConverter，不再使用默认的  SimpleMessageConverter
	 * 
	 * 注意：同一个消息的发送和接收要使用相同的 messageConverter，要不然消息不能被正确的解析
	 */
	@Bean
	public MessageConverter messageConverter () {
		return new Jackson2JsonMessageConverter();
	}
	
}
