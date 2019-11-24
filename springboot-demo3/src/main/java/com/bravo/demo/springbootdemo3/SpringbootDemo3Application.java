package com.bravo.demo.springbootdemo3;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Bobby
 * @since 2019/10/13 14:52
 */
@SpringBootApplication
public class SpringbootDemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo3Application.class, args);
    }

    @Bean
    public MapperFactory getFactory() {
        return new DefaultMapperFactory.Builder().build();
    }
}
