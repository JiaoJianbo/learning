package com.bravo.interview.juc;

import sun.font.FontRunIterator;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bobby
 *
 * 锁绑定多个条件（Condition）
 *   - Condition.await()    -- 类似 Object.wait(), 让当前线程等待，直到被唤醒或中断
 *   - Condition.signal()   -- 类似 Object.notify, 但与其不同的是可以精确唤醒某个线程
 * 类似 wait 和 notify, 调用 await 和 signal 之前也要确保已获得锁
 *
 * 题目：
 *   多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 *   A打印5次，B打印10次，C打印15次
 *   ......
 *   打印10轮
 */
public class ReentrantLockDemo5 {

    public static void main(String[] args) {
        PrintResource pr = new PrintResource();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                pr.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                pr.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                pr.print15();
            }
        }, "C").start();
    }
}

class PrintResource {
    private int number = 1;//A:1.B:2,C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try{
            //1判断
            while(number != 1){
                c1.await();
            }
            //2干活
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3通知
            number = 2;
            // 调用之前，需要获取到创建该对象的锁，否则会产生 java.lang.IllegalMonitorStateException 异常
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //1判断
            while(number != 2){
                c2.await();
            }
            //2干活
            for(int i=1;i<=10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3通知
            number = 3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //1判断
            while(number != 3){
                c3.await();
            }
            //2干活
            for(int i=1;i<=15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3通知
            number = 1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
