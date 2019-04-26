package com.bravo.demo.ssm;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
@MapperScan("com.bravo.demo.ssm.dao") //加这个注解后，DAO可以不用单独再加 @Mapper注解
// The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration and @ComponentScan with their default attributes
@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan

// Using the exclude attribute to disable specific auto-configuration. 
// If the class is not on the classpath, you can use the excludeName attribute of the annotation and specify the fully qualified name instead.
//@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
//@EnableSwagger2 //访问 swagger-ui.html
@EnableCaching  // 开启缓存注解
public class App {
	private static Logger log = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@RequestMapping("/")
	public String hello() {
		log.debug("DEBUG, visit home page ...");
		log.info("INFO, visit home page ...");
		//return "forward:/home.html"; // forward请求，不会显示在地址栏
		return "redirect:/home.html"; // redirect请求，会显示在地址栏
	}

}
