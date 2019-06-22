package com.bravo.interview.juc.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Bobby
 *
 * 与其他 BlockingQueue 不同，SynchronousQueue是一个不存储元素的 BlockingQueue。
 * 每一个 put 操作必须要等待一个 take 操作，否则不能继续添加元素，反之亦然。
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
