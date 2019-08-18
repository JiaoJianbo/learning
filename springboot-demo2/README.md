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
 