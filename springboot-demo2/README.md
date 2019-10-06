# Spring Boot Feature Demo2

所有功能基于 Spring Boot 2.1.x, 主要包含以下功能：

1. Spring Data JPA  
 JpaSpecificationExecutor 接口的用法。参考 `com.bravo.demo.springbootdemo2.repo.EmployeeRepositoryTest`。
 
2. 根据实体自动生成建表脚本，参考 yaml 文件配置
  
    ```yaml
    javax.persistence.schema-generation.scripts.action: create-drop
    javax.persistence.schema-generation.scripts.create-target: "./target/create.sql"
    javax.persistence.schema-generation.scripts.drop-target: "./target/create.sql"
    ```
 
3. 联合主键的另一种写法  
  参考 Person, PersonKey 的写法。仅使用 @IdClass 注解，并没有使用 @EmbeddedId 和 @Embeddable 注解。并且主类中（Person）还要重复定义主键类（PersonKey）中的属性（name, idCard）。

4. 使用 [jasypt-spring-boot](https://github.com/ulisesbocchio/jasypt-spring-boot) 对配置参数加密

5. Spring Data 中 @CreatedDate, @LastModifiedDate 的使用  
  "can be used on properties of type Joda-Time, DateTime, legacy Java Date and Calendar, JDK8 date and time types, and long or Long"。
  其实在 Hibernate 中也有类似的注解 @UpdateTimestamp 和 @CreationTimestamp。

6. Hibernate 注解 @DynamicInsert 和 @DynamicUpdate 的用法  
  参考 [Hibernate利用@DynamicInsert和@DynamicUpdate生成动态SQL语句](https://www.cnblogs.com/quanyongan/p/3152290.html)
