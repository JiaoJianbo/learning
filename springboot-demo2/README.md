# Spring Boot Feature Demo2

所有功能基于 Spring Boot 2.1.x, 主要包含以下功能：

1. Spring Data JPA  
 JpaSpecificationExecutor 接口的用法
 
2. 根据实体自动生成建表脚本，参考 yaml 文件配置
  
    ```yaml
    javax.persistence.schema-generation.scripts.action: create-drop
    javax.persistence.schema-generation.scripts.create-target: "./target/create.sql"
    javax.persistence.schema-generation.scripts.drop-target: "./target/create.sql"
   ```
 
3. 联合主键的另一种写法  
  参考 Person, PersonKey 的写法。仅使用 @IdClass 注解，并没有使用 @EmbeddedId 和 @Embeddable 注解。并且主类中（Person）还要重复定义主键类（PersonKey）中的属性（name, idCard）。
