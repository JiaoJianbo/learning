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
    


