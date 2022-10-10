# Spring Boot Feature Demo7

主要包含以下功能：

1. Spring Boot 2 使用 JUnit5

    JUnit5 由三个不同子项目的几个不同模块组成。  
    
    **JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage**
    
    1）**JUnit Platform**: 是在JVM上启动测试框架的基础，不仅支持Junit自制的测试引擎，其他测试引擎也都可以接入。
    
    2）**JUnit Jupiter**: 提供了JUnit5的新的编程模型，是JUnit5新特性的核心。内部包含了一个测试引擎，用于在Junit Platform上运行。
    
    3）**JUnit Vintage**: 由于JUint已经发展多年，为了照顾老的项目，其提供了兼容JUnit4.x,Junit3.x的测试引擎。

    **参考:**  
    [SpringBoot 实战：JUnit5+MockMvc+Mockito 做好单元测试](https://juejin.cn/post/7036140165944836104)  
    [SpringBoot 单元测试——JUnit5](https://blog.csdn.net/weixin_44302046/article/details/124285058)  
    [Spring Boot 单元测试（一）JUnit5](https://juejin.cn/post/7041110340720934919)  