package com.bravo.interview.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Bobby
 *
 * 对ReentrantReadWriteLock，其读锁是共享锁，其写锁是独占锁。
 * 读锁的共享锁可保证并发读是非常高效的，读写、写读、写写的过程是互斥的。
 *
 * 读-读 共存
 * 读-写 不能共存
 * 写-写 不能共存
 * 写操作 原子+独占，整个过程必须是一个完整的统一体，中间不许被分割，被打断。
 */
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i <5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }).start();
        }

        for (int i = 0; i <5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            }).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> cache = new HashMap<>(); // 不要忘记 volatile
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 正在写入 " + key);
            TimeUnit.MILLISECONDS.sleep(300);
            cache.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入数据完成。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + " 正在读取 " + key);
            TimeUnit.MILLISECONDS.sleep(300);
            Object obj = cache.get(key);
            System.out.println(Thread.currentThread().getName() + " 读取数据完成。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}