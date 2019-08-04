package com.bravo.demo.springboot.controller;

import com.bravo.demo.springboot.config.MyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Bobby
 * @since 2019/7/27 22:04
 */

@RestController
public class HelloController {

    @Value("${my-config.enabled}")
    private boolean enabled;

    @Value("${my-config.count}")
    private Long count;

//    @Value("${myConfig.employeeList}")
//    private List<Employee> employeeList = new ArrayList<>();

    @Value("${hello.defaultName}")
    private String defaultName;

    @Autowired
    private MyConfig myConfig;

    @GetMapping("/hello")
    public String sayHi() {
        if(enabled){
            System.out.println("count == " + count);
            System.out.println("myConfig.getEmployeeList() == " + myConfig.getEmployeeList());
        }
        return "Hello " + defaultName;
    }

    @GetMapping("/hello/{name}")
    public String sayHi(@PathVariable(value = "name")  String name) {
        return "Hello " + name;
    }
}
