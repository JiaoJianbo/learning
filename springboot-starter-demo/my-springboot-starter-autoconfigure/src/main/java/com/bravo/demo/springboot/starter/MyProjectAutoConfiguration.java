package com.bravo.demo.springboot.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication // 在 Web 环境才生效
@EnableConfigurationProperties(MyProperties.class) // 使配置类 MyProperties 生效
public class MyProjectAutoConfiguration {
	
	@Autowired
	private MyProperties myProperties;

	@Bean
	@ConditionalOnMissingBean(name = "myProjectService")
	public MyProjectService myProjectService() {
		MyProjectService myProjectService = new MyProjectService();
		myProjectService.setMyProperties(myProperties);
		return myProjectService;
	}
}
