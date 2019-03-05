# Dropwizard


Referred to: [Getting Started | Dropwizard](https://www.dropwizard.io/1.3.9/docs/getting-started.html) 

Create this project using:

```
mvn archetype:generate -DarchetypeGroupId=io.dropwizard.archetypes -DarchetypeArtifactId=java-simple -DarchetypeVersion=1.3.9
```

Here using the version of Dropwizard is **1.3.9**.

How to start the Dropwizard application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/dropwizard-tutorial-0.0.1-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
