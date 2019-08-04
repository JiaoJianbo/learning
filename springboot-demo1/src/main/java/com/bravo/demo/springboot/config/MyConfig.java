package com.bravo.demo.springboot.config;

import com.bravo.demo.springboot.entity.Employee;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * YAML 中配置复杂对象
 * 详情请见: https://jiaojianbo.github.io/2019/07/28/yaml-config-object/
 *
 * @author Bobby
 * @since 2019/7/27 22:03
 */

@ConfigurationProperties(prefix = "my-config")
@Component
//@EnableConfigurationProperties(MyConfig.class)
//@Configuration
@Data
public class MyConfig {
    private boolean enabled = false;

    private Long count = 0L;

    private String itemName;

    private List<Employee> employeeList = new ArrayList<>();
}
