package org.springframework.boot.actuate.autoconfigure.endpoint.jmx;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bobby
 * @since 2019/9/22 23:37
 */
@Configuration
@AutoConfigureAfter(JmxAutoConfiguration.class)
@EnableConfigurationProperties(JmxEndpointProperties.class)
public class JmxEndpointAutoConfiguration {
    private final ApplicationContext applicationContext;

    private final JmxEndpointProperties properties;

    public JmxEndpointAutoConfiguration(ApplicationContext applicationContext, JmxEndpointProperties properties) {
        this.applicationContext = applicationContext;
        this.properties = properties;
    }

}
