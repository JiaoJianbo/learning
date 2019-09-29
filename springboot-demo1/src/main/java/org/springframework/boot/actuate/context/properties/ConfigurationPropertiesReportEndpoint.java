package org.springframework.boot.actuate.context.properties;

import org.springframework.beans.BeansException;
import org.springframework.boot.actuate.endpoint.Sanitizer;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Bobby
 * @since 2019/9/22 23:32
 */
@Endpoint(id = "configprops")
public class ConfigurationPropertiesReportEndpoint implements ApplicationContextAware {
    private static final String CONFIGURATION_PROPERTIES_FILTER_ID = "configurationPropertiesFilter";

    private final Sanitizer sanitizer = new Sanitizer();

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    public void setKeysToSanitize(String... keysToSanitize) {
        this.sanitizer.setKeysToSanitize(keysToSanitize);
    }

    @ReadOperation
    public ApplicationConfigurationProperties configurationProperties() {
        return extract(this.context);
    }

    private ApplicationConfigurationProperties extract(ApplicationContext context) {
        Map<String, ContextConfigurationProperties> contextProperties = new HashMap<>();
        ApplicationContext target = context;
        while (target != null) {
//            contextProperties.put(target.getId(), describeConfigurationProperties(target, getObjectMapper()));
//            target = target.getParent();
        }
        return new ApplicationConfigurationProperties(contextProperties);
    }



    /**
     * A description of an application's {@link ConfigurationProperties} beans. Primarily
     * intended for serialization to JSON.
     */
    public static final class ApplicationConfigurationProperties {

        private final Map<String, ContextConfigurationProperties> contexts;

        private ApplicationConfigurationProperties(Map<String, ContextConfigurationProperties> contexts) {
            this.contexts = contexts;
        }

        public Map<String, ContextConfigurationProperties> getContexts() {
            return this.contexts;
        }

    }

    /**
     * A description of an application context's {@link ConfigurationProperties} beans.
     * Primarily intended for serialization to JSON.
     */
    public static final class ContextConfigurationProperties {

        private final Map<String, ConfigurationPropertiesBeanDescriptor> beans;

        private final String parentId;

        private ContextConfigurationProperties(Map<String, ConfigurationPropertiesBeanDescriptor> beans,
                                               String parentId) {
            this.beans = beans;
            this.parentId = parentId;
        }

        public Map<String, ConfigurationPropertiesBeanDescriptor> getBeans() {
            return this.beans;
        }

        public String getParentId() {
            return this.parentId;
        }

    }

    /**
     * A description of a {@link ConfigurationProperties} bean. Primarily intended for
     * serialization to JSON.
     */
    public static final class ConfigurationPropertiesBeanDescriptor {

        private final String prefix;

        private final Map<String, Object> properties;

        private ConfigurationPropertiesBeanDescriptor(String prefix, Map<String, Object> properties) {
            this.prefix = prefix;
            this.properties = properties;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public Map<String, Object> getProperties() {
            return this.properties;
        }

    }
}
