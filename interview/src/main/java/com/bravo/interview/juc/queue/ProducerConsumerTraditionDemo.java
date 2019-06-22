package com.bravo.interview.juc.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Bobby
 *
 * 步骤：1.判断 2.干活 3.通知
 *
 * 注意：防止虚假唤醒 (interrupts and spurious wakeups are possible, and this method (.wait) should always be used in a loop)
 */
public class ProducerConsumerTraditionDemo {
    public static void main(String[] args) {
        Resource res = new Resource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    res.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Produce").start();


        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    res.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consume").start();
    }

}

class Resource {
    private int resNum = 0;
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void produce() throws InterruptedException {
        /*synchronized (this){
            while (resNum  != 0){
                this.wait();
            }
            resNum++;
            System.out.println(Thread.currentThread().getName() + " ++> " + resNum);

            this.notifyAll();
        }*/

        lock.lock();
        try{
            // 1. 判断
            while (resNum != 0){
                condition.await(); //等待，停止生产
            }

            // 2. 干活
            resNum++;
            System.out.println(Thread.currentThread().getName() +  " ++++++> " + resNum);

            // 3. 通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        /*synchronized (this){
            while (resNum == 0){
                this.wait();
            }
            resNum --;
            System.out.println(Thread.currentThread().getName() + " --> " + resNum);

            this.notifyAll();
        }*/


        lock.lock();
        try{
            // 1. 判断
            while (resNum == 0){
                condition.await(); //等待，停止消费
            }

            // 2. 干活
            resNum--;
            System.out.println(Thread.currentThread().getName() +  " -----> " + resNum);

            // 3. 通知唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
