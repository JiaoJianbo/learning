package com.bravo.demo.springbootdemo7.service;

import org.springframework.stereotype.Service;

/**
 * @author Bobby
 * @since 2022/10/9 11:23
 */
@Service
public class HelloService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
