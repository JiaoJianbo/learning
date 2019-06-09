package com.bravo.interview.juc.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Bobby
 *
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        SynchronousQueue synchronousQueue = new SynchronousQueue();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    synchronousQueue.put(i);
                    System.out.println(Thread.currentThread().getName() + " put " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AAA").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    Object o = synchronousQueue.take();
                    System.out.println(Thread.currentThread().getName() + " take " + o);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BBB").start();
    }
}
