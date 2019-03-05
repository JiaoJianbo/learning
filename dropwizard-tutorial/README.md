# Dropwizard

Referred to: [Getting Started | Dropwizard](https://www.dropwizard.io/1.3.9/docs/getting-started.html) 

Create this project using:

```
mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=1.3.9
```

Here we are using the version of Dropwizard is **1.3.9**.

How to start the Dropwizard application
---

1. Run `mvn clean install` or `mvn clean package` to build your application
1. Start application with `java -jar target/dropwizard-tutorial-0.0.1-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`

Note
---
1. When we run `mvn package` to build the application, actually to build a fat JAR via `maven-shade-plugin`
1. When you run the application via `java -jar xxx.jar`, Dropwizard needs other command line arguments. The first argument is `server` (In this case, the only command available is `server`), and the second argument is the YAML configuration file.
1. Can check the Metrics on the admin port: `http://localhost:8081/`
1. When a Jetty worker thread is handling an incoming HTTP request, the thread name is set to the method and URI of the request.
1. [The user manual of Dropwizard 1.3.9](https://www.dropwizard.io/1.3.9/docs/manual/index.html#manual-index)

