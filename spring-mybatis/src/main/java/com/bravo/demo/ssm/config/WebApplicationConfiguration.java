package com.bravo.demo.ssm.config;

import javax.servlet.Filter;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebApplicationConfiguration extends WebMvcAutoConfiguration{

	//通过Etag缓存
	@Bean
	public Filter etagFilter() {
		return new ShallowEtagHeaderFilter();
	}
}
