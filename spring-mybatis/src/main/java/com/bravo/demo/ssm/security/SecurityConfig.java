package com.bravo.demo.ssm.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SecurityProperties.class) //使SecurityProperties配置生效
public class SecurityConfig {
}
