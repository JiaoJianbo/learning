package com.bravo.demo.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Bobby
 * @since 2019/12/8 22:05
 */
@Component
public class MyJob {
    private Integer count1 = 0;
    private Integer count2 = 0;

    @Autowired
    private JobMetrics jobMetrics;

    @Async("main")
    @Scheduled(fixedDelay = 1000)
    public void doJob1(){
        count1 ++;

        jobMetrics.job1Counter.increment();
        jobMetrics.map.put("x", Double.valueOf(count1));
        System.out.println("task1 count = " + count1);
    }

    @Async("main")
    @Scheduled(fixedDelay = 1000)
    public void doJob2(){
        count2 ++;

        jobMetrics.job2Counter.increment();
        System.out.println("task2 count = " + count2);
    }
}
