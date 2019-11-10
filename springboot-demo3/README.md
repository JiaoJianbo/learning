# Spring Boot Feature Demo3

主要包含以下功能：

1. [Spring Data REST](https://spring.io/projects/spring-data-rest) 入门

    在 Repository 上加上如下注解，根本不需要 Controller。启动服务，就可以提供 REST 服务。
    
    ```java
    @RepositoryRestResource(collectionResourceRel = "person", path = "person")
    ```
    
    如果此时 `person` 表中有三条数据，访问 `http://localhost:8080/person/`，便可得到如下结果：
    
    ```json
    {
      "_embedded" : {
        "person" : [ {
          "firstName" : "Jim",
          "lastName" : "Green",
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/person/1"
            },
            "person" : {
              "href" : "http://localhost:8080/person/1"
            }
          }
        }, {
          "firstName" : "Meimei",
          "lastName" : "Han",
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/person/2"
            },
            "person" : {
              "href" : "http://localhost:8080/person/2"
            }
          }
        }, {
          "firstName" : "Lei",
          "lastName" : "Li",
          "_links" : {
            "self" : {
              "href" : "http://localhost:8080/person/3"
            },
            "person" : {
              "href" : "http://localhost:8080/person/3"
            }
          }
        } ]
      },
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/person{?page,size,sort}",
          "templated" : true
        },
        "profile" : {
          "href" : "http://localhost:8080/profile/person"
        }
      },
      "page" : {
        "size" : 20,
        "totalElements" : 3,
        "totalPages" : 1,
        "number" : 0
      }
    }
    ```
2. 引入 [HAL browser](https://github.com/mikekelly/hal-browser)，可以通过访问 http://127.0.0.1:8080/browser/index.html 来浏览和测试 API。

3. 使用 [JSONAssert](https://github.com/skyscreamer/JSONassert) 比较两个 JSON 是否相等。

    JSONAssert 已被`spring-boot-starter-test` 默认集成。如果要单独使用，可以引入 Maven 依赖。
    ```xml
    <dependency>
        <groupId>org.skyscreamer</groupId>
        <artifactId>jsonassert</artifactId>
        <version>1.5.0</version>
    </dependency>
    ```
    
    **参考:**  
    [JSONassert Cookbook](http://jsonassert.skyscreamer.org/cookbook.html)  
    [JSONAssert – How to unit test JSON data](https://www.mkyong.com/java/jsonassert-how-to-unit-test-json-data/)  
    [Spring REST Integration Test Example](https://www.mkyong.com/spring-boot/spring-rest-integration-test-example/)  
    [JSONassert 1.5.1-SNAPSHOT API](http://jsonassert.skyscreamer.org/apidocs/index.html)
    
4. 启用 HTTPS 访问方式。
    
    1). 使用命令`keytool -genkey -alias mytomcat -keyalg RSA -keystore D:\keystore.jks -keysize 2048`生成证书。

    ```text
    C:\Users\Bobby>keytool -genkey -alias mytomcat -keyalg RSA -keystore D:\keystore.jks -keysize 2048
    输入密钥库口令:
    再次输入新口令:
    您的名字与姓氏是什么?
      [Unknown]:
    您的组织单位名称是什么?
      [Unknown]:
    您的组织名称是什么?
      [Unknown]:
    您所在的城市或区域名称是什么?
      [Unknown]:
    您所在的省/市/自治区名称是什么?
      [Unknown]:
    该单位的双字母国家/地区代码是什么?
      [Unknown]:
    CN=Unknown, OU=Unknown, O=Unknown, L=Unknown, ST=Unknown, C=Unknown是否正确?
      [否]:  Y
    
    输入 <mytomcat> 的密钥口令
            (如果和密钥库口令相同, 按回车):
    
    Warning:
    JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore D:\keystore.jks -destkeystore D:\keystore.jks -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
    ```
    
    2). 拷贝上面生成的证书到 resource 目录下，并在 application.yml 中添加如下配置，这里的 password 就是刚才生成证书时输入的。
    
    ```yaml
    server:
      port: 8443
      ssl:
        key-store: classpath:keystore.jks
        key-password: my@secret
        key-store-password: my@secret
    ```
    
    3). 如果需要将 HTTP 请求自动重定向到 HTTPS 请求端口，可以添加 `com.bravo.demo.springbootdemo3.config.TomcatConfig` 中的配置。
    
    **参考:**  
    [常用的Java Keytool Keystore命令](https://csr.chinassl.net/keytool-commands.html)  
    [Spring Boot 支持 Https 有那么难吗？](https://segmentfault.com/a/1190000020052375)  
    
    