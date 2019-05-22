package com.bravo.interview.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Bobby
 *
 * volatile是Java虚拟机提供的轻量级的同步机制。能保证可见性、有序性（禁止指令重排），但不能保证原子性。
 *
 * 要保证这种操作的原子性，请使用 AtomicInteger.
 */
public class VolatileDemo {
    static volatile int counter = 0;
    static AtomicInteger atomicCounter = new AtomicInteger(0);

    public static void main(String[] args) {

        for(int i=0; i<20; i++){
            new Thread(() -> {
                for(int j=0; j<1000; j++){
                    counter++;
                    atomicCounter.getAndIncrement();
                }
            }, "T-" + String.valueOf(i)).start();
        }

        while (Thread.activeCount() > 2) { //直到所有线程运行完
            Thread.yield();
        }

        // 如果能保证原子性，那么最后 counter 的值应该是 20*1000=20000，但是实际却不是这样。
        // 而且这也说明 i++ 自增操作不是原子操作
        System.out.println("counter = " + counter);
        System.out.println("atomicCounter = " + atomicCounter.get());
    }
}

