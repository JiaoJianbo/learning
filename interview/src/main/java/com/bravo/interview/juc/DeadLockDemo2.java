package com.bravo.interview.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author: Bobby
 *
 * 1. jps -l
 * 2. jstack [vmid]
 */
public class DeadLockDemo2 {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(()->{
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName() + " got lock1");
                try { TimeUnit.SECONDS.sleep(1L); } catch (InterruptedException e) { e.printStackTrace(); }

                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName() + " got lock2");
                }
            }
        }, "T-1").start();


        new Thread(()->{
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " got lock2");
                try { TimeUnit.SECONDS.sleep(1L); } catch (InterruptedException e) { e.printStackTrace(); }

                synchronized (lock1){
                    System.out.println(Thread.currentThread().getName() + " got lock1");
                }
            }
        }, "T-2").start();
    }
}
