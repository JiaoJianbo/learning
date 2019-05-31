package com.bravo.interview.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: Bobby
 *
 * Semaphore 主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        // 10 个线程抢夺 3 个资源
        for(int i=0; i<10; i++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到资源。");
                    try{TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println(Thread.currentThread().getName() + " 释放资源。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
