package com.bravo.demo.springbootdemo7.controller;

import com.bravo.demo.springbootdemo7.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bobby
 * @since 2022/10/9 11:23
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam("name") String name) {
        log.info("name === {}", name);
        return helloService.sayHello(name);
    }
}
