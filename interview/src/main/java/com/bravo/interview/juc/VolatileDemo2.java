package com.bravo.interview.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author: Bobby
 *
 * volatile 适用场景：
 * 1. 运算结果不依赖当前变量值，或者能够确保只有单一的线程能修改变量的值。
 * 2. 变量不需要与其他的状态变量共同参与不变约束。
 */
public class VolatileDemo2 {
    private volatile boolean flag = true;
//    private boolean flag = true;

    public void doWork() throws InterruptedException {
        while(flag){
            System.err.println(Thread.currentThread().getName() + " do something... ");
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    public void shutdown(){
        this.flag = false;
        System.err.println("shutdown!");
    }


    public static void main(String[] args) throws InterruptedException {
        VolatileDemo2 demo = new VolatileDemo2();

        for (int i = 0; i <10; i++) {
            new Thread(() -> {
                try {
                    demo.doWork();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        TimeUnit.SECONDS.sleep(3);
        demo.shutdown();
    }
}
