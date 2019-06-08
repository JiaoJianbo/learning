package com.bravo.interview.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Bobby
 *
 * 闭锁, 让一些线程阻塞直到另一个线程完成一系列操作后才被唤醒。FutrueTask 也可以用于闭锁操作，FutureTask.get() 会等待运算结果。
 *
 * CountDownLatch 主要有两个方法，当一个或多个线程调用 await 方法时，调用线程会被阻塞。
 * 其他线程调用 countDown 方法会将计数器减1（调用 countDown 方法的线程不会阻塞），
 * 当计数器的值变为 0 时，因调用 await 方法被阻塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " completed.");
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed.");
    }

}
