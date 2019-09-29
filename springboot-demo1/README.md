# Spring Boot Feature Demo

所有功能基于 Spring Boot 2.1.x

1. YAML 中配置复杂对象  
  参考 config.MyConfig, controller.HelloController  

2. 自定义 HealthIndicator  
  参考 actuate.MyHealthIndicator 和 application.yml 的配置
  
3. 自定义 info endpoint 的属性 (actuator 中的 info endpoint)   
  参考 application.yml 的配置

4. 使用 FastJson 代替 Spring Boot 默认使用的Jackson 进行 JSON 操作  
  1). POM 中引入 fastjson，排除 jackson。注意常用模块 web 和 actuator 中都使用了 Jackson API，因此首先需要排除引用。
  
  2). 要保证 JSON 格式数据的转换，需要重定义 HttpMessageConverters bean。
  
  3). 要保证 actuator 的使用，需要重写个别类。本例中 org.springframework.boot.actuate 包下的内容是对原有类的重写，因为原有类中直接引用了 Jackson 的 API。

  4). 参考 [springboot配置FastJson为Spring Boot默认JSON解析框架](https://www.cnblogs.com/soul-wonder/p/9052422.html)


**引申思考**：众所周知，Spring Boot 对好多常见工具或者框架做了集成，在实现中用到了这些框架的 API (比如 import org.elasticsearch.client.RestClient;)
但是，通常我们并不会导入这些我们没用到的库或者 API，那 Spring Boot 为什么没有编译报错，使用中也没有报 ClassNotFoundException 呢？



### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
