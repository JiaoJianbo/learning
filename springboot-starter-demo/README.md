自定义 Spring Boot Starter （场景启动器）
---
1. 该 Starter 需要使用的依赖有哪些？

2. 如何编写自动配置。（参考其他 AutoConfiguration 类）  
	* @Configuration                                 指定该类是个配置类  
	* @ConditionalOnXxx                              在指定条件成立时，配置生效  
	* @AutoConfigureOrder                            指定自动配置的顺序  
	* @AutoConfigureBefore  
	* @AutoConfigureAfter  
	* @ConfigurationProperties(prefix="my.bravo")    结合 XxxProperties 类绑定相关配置  
	* @EnableConfigurationProperties                 启用 XxxProperties 配置类，并加入到容器中  
	* @Bean                                          给容器中添加指定的 Bean  

	将需要启动就加载的自动配置类，配置在  META-INF/spring.factories 文件中。例如：
	
	```properties
	# Auto Configure
	org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
	org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
	org.springframework.boot.autoconfigure.aop.AopAutoConfiguration
	```

3. 启动器（Starter）只用来做依赖导入，专门写一个自动配置模块，启动器依赖自动配置。这样提供给第三方时，只需要引入启动器即可。  

4. 命名规约：xxx-starter, xxx-starter-autoconfigure  
	* 官方：  
		- 前缀：spring-boot-starter-  
		- 模式：spring-boot-starter-模块名  
		- 例如：spring-boot-starter-web, spring-boot-starter-aop  
	* 自定义：  
		- 后缀：-spring-boot-starter  
		- 模式：模块名-spring-boot-starter  
		- 例如：mybatis-spring-boot-starter  
	
		

