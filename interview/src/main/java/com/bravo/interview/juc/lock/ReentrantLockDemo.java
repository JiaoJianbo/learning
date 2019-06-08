package com.bravo.interview.juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bobby
 *
 * 比较加锁和不加锁的运行结果，可以看出，加锁后可以保证线程安全。即每次运行结果都是 20*1000=20000。
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        IncreasementRes res = new IncreasementRes();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    res.increase();
                }
            }).start();
        }

        //TimeUnit.SECONDS.sleep(3);
        while (Thread.activeCount() > 2){ // 直到所有线程运行结束
            Thread.yield();
        }
        System.out.println("res.getValue() = " + res.getValue());
    }
}


class IncreasementRes{
    private final ReentrantLock lock = new ReentrantLock();
    private int i = 0;

    public void increase(){
        lock.lock();

        try {
            i+=1;
        } finally {
            lock.unlock();
        }
    }

    public int getValue() {
        return i;
    }
}
