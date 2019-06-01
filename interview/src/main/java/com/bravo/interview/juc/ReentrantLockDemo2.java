package com.bravo.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bobby
 *
 * 公平锁示例
 *
 * 在 JDK5.0 之前，重入锁（ReentrantLock）的性能远远好于 synchronized 关键字。
 * 在 JDK6.0 之后 synchronized 得到了大量的优化，二者性能也不分伯仲。但是重入锁是可以完全替代 synchronized 关键字的。
 *
 * 重入锁有一系列特性是 synchronized 不具有的：
 * 1. 公平锁  -- 但是请注意，锁的公平性并不保证线程调度的公平性。因此，使用公平锁的多个线程中的一个可能会在其他活动线程没有进展或当前没有持有锁的情况下连续多次获得该锁。
 * 2. 可中断响应
 * 3. 限时申请等待
 * 4. 可以绑定多个条件
 *
 */
public class ReentrantLockDemo2 {
    public static void main(String[] args) {
        FairResource res = new FairResource();
        for (int i = 0; i < 2; i++) {
            new Thread(res).start();
        }
    }
}

class FairResource implements Runnable{
    private final ReentrantLock lock = new ReentrantLock(true);
//    private final ReentrantLock lock = new ReentrantLock(); // 默认非公平锁

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            lock.lock();

            try {
                System.out.println(Thread.currentThread().getName() + " get the lock.");
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}