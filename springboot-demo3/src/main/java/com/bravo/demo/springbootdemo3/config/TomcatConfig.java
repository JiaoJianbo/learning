package com.bravo.demo.springbootdemo3.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 为了配置 HTTPS 而添加的配置。将用户发起的 HTTP 请求，自动转发到 HTTPS 上。
 *
 * @author Bobby
 * @since 2019/11/10 23:22
 */
@Configuration
public class TomcatConfig {
    /**
     * 配置了 HTTP 的请求端口为 8080，所有来自 8080 的请求，将被自动重定向到 8443 这个 HTTPS 的端口上。
     * @return
     */
    @Bean
    public Connector connector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);

        return connector;
    }

    /*
     * 也有说用 EmbeddedServletContainerFactory 或 TomcatEmbeddedServletContainerFactory，
     * 但是在 Spring Boot 2.x 中要用 TomcatServletWebServerFactory。
     */
    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(connector()); // 添加上面的 connector
        return tomcat;
    }
}
