package com.bravo.demo.ssm.config;

import java.net.UnknownHostException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.bravo.demo.ssm.entity.User;

import redis.clients.jedis.Jedis;

/*
 * 参考 org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 */
@Configuration
@ConditionalOnClass({ JedisConnection.class, RedisOperations.class, Jedis.class })
public class MyRedisConfig {

//	// 该 redisTemplate 只适用于 User对象。可将User对象序列化为JSON格式
//	@Bean
//	public RedisTemplate<Object, Object> userRedisTemplate(RedisConnectionFactory redisConnectionFactory) 
//			throws UnknownHostException {
//		RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
//		template.setConnectionFactory(redisConnectionFactory);
//		// 参考org.springframework.data.redis.serializer.RedisSerializer的实现类
//		// 将对象转为JSON，这里使用具体的类，如果使用Object，可能引起反序列化错误
//		RedisSerializer<User> serializer = new Jackson2JsonRedisSerializer<User>(User.class);
//		// 改变默认的序列化器，默认使用JDK Serialization技术
//		template.setDefaultSerializer(serializer);
//		return template;
//	}
}
