package com.bravo.demo.springboot.actuate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author Bobby
 * @since 2019/8/4 14:13
 *
 * 自定义 HealthIndicator
 * 1. 实现 HealthIndicator 接口
 * 2. 命名 XxxHealthIndicator
 * 3. 加入容器中
 */
@Component
@Slf4j
public class MyHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        int random = new Random().nextInt(10);
        log.info("random = {}", random);

        if(random % 2 == 0){
            builder.up().withDetail("msg", "Service is up.");

        }else {
            builder.down().withDetail("msg", "Service is not available.");
        }
    }
}
