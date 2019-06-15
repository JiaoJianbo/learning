package com.bravo.demo.ssm.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bravo.demo.ssm.entity.User;

/*
 * 配合测试 RabbitListener， 使用这个 controller 向 RabbitMQ 发送数据。检查后台 RabbitListener 的打印日志
 */
@RestController
@RequestMapping ("/message")
public class MessageController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	// 这里接口的命名不一定符合 REST 风格，仅仅是为了测试
	@GetMapping("/send")
	public String sendTestMessage() {
		String exchange = "exchange.fanout";
		
		User user = new User();
		user.setUsername("Bobby");
		user.setGender("M");

		rabbitTemplate.convertAndSend(exchange, "", user); // 广播不需要 routingKey
		return HttpStatus.OK.getReasonPhrase();
	}
}
