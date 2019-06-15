package com.bravo.demo.ssm.amqp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.bravo.demo.ssm.entity.User;

/*
 * @RabbitListener 配合 @EnableRabbit，监听消息队列
 * 
 * 但相应的 queue 中有消息到达时，会自动执行 @RabbitListener 的方法
 */
//平时不常用 MQ 这里先注掉这些 RabbitListener，否则后台一直尝试连接 MQ
@Service
public class RabbitListenerService {
	private static Logger logger = LoggerFactory.getLogger(RabbitListenerService.class);

//	@RabbitListener(queues = "bravo.queue")
	public void ReceiveUserMsg(User user) {
		logger.info("Received message: {}", user);
	}
	
//	@RabbitListener(queues = "bobby.queue")
	public void ReceiveMsg(Message message) {
		logger.info("Received message body: {}", new String(message.getBody()));
		logger.info("Received message properties: {}", message.getMessageProperties());
	}
}
