package com.bravo.interview.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bobby
 *
 * 限时申请锁
 */
public class ReentrantLockDemo4 {
    public static void main(String[] args) {
        TryLockResource res = new TryLockResource();

        new Thread(res, "AAA").start();
        new Thread(res, "BBB").start();
    }
}

class TryLockResource implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if(lock.tryLock(1L, TimeUnit.SECONDS)){ //尝试在1秒钟内获取锁，否则退出。
                System.out.println(Thread.currentThread().getName() + " holds the lock.");
                TimeUnit.SECONDS.sleep(2L);
            } else {
                System.out.println(Thread.currentThread().getName() + " try lock failed.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock.isHeldByCurrentThread()) {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " releases the lock.");
            }
        }

    }
}
