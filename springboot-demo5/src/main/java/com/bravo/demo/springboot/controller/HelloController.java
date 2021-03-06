package com.bravo.demo.springboot.controller;

import com.bravo.demo.springboot.entity.Agent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bobby
 * @since 2019/12/01 22:44
 */

@RestController
public class HelloController {
    @GetMapping("/hi")
    public Agent getEmp(){
        return new Agent("007", "James Bond");
    }

    @GetMapping("/hello/{name}")
    public String sayHi(@PathVariable(value = "name")  String name) {
        return "Hello " + name;
    }
}
