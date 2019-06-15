package com.bravo.demo.ssm.amqp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bravo.demo.ssm.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitMQTest {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	//也是 RabbitAutoConfiguration 中自动注入的一个组件，用来创建和删除 Exchange，Queue，Binding 等。
	@Autowired
	private AmqpAdmin amqpAdmin;

	@Test
	public void sendDirectMsg() {
		String exchange = "exchange.direct";
		String routingKey = "bobby.queue";
		// 自己构造并发送 message
		// rabbitTemplate.send(exchange, routingKey, message);
		
		// 默认使用 SimpleMessageConverter，基本就是使用 JDK 序列化
		// rabbitTemplate.convertAndSend(exchange, routingKey, object);
		
		// 使用 SimpleMessageConverter, 下面的消息的 Payload 368 bytes.
		// 使用 Jackson2JsonMessageConverter， Payload 为 65 bytes.
		Map<String, Object> msgObj = new HashMap<>();
		msgObj.put("msg", "这是一个direct 消息");
		msgObj.put("list", Arrays.asList("嘿嘿嘿", 123, true));
		
		rabbitTemplate.convertAndSend(exchange, routingKey, msgObj);
	}
	
	@Test
	public void receiveDirectMsg() {
		//String queueName = "bobby.queue";
		String queueName = "bobby";
		Object obj = rabbitTemplate.receiveAndConvert(queueName);
		System.out.println(obj.getClass());
		System.out.println(obj);
	}
	
	@Test
	public void sendFanoutMsg() {
		String exchange = "exchange.fanout";
		
		User user = new User();
		user.setUsername("Bobby");
		user.setGender("M");

		rabbitTemplate.convertAndSend(exchange, "", user); // 广播不需要 routingKey
	}
	
	@Test
	public void createRules() {
		// 创建一个 Exchange
		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
		// 创建一个 Queue
		amqpAdmin.declareQueue(new Queue("amqpadmin.queue", true));
		// 创建绑定规则，绑定 Exchange 和 Queue
		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", DestinationType.QUEUE, "amqpadmin.exchange", "adqpadmin", null));
		System.out.println("创建完毕。");
	}
	
	@Test
	public void deleteRules() {
		amqpAdmin.deleteQueue("amqpadmin.queue");
		amqpAdmin.deleteExchange("amqpadmin.exchange");
		System.out.println("删除完毕。");
	}
}
