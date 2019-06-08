package com.bravo.interview.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁示例
 * @author Bobby
 */
public class SpinLockDemo {
    public static void main(String[] args) {
        SpinLock lock = new SpinLock();

        new Thread(() -> {
            lock.lock();
            try{TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            lock.unlock();
        }, "AAA").start();

        new Thread(() -> {
            lock.lock();
            try{TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            lock.unlock();
        }, "BBB").start();

    }
}

class SpinLock {
    AtomicReference<Thread> reference = new AtomicReference();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " coming in");
        while(!reference.compareAndSet(null, thread)){
        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        reference.compareAndSet(thread, null);
        System.out.println(thread.getName() + " exit.");
    }
}
