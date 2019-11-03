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