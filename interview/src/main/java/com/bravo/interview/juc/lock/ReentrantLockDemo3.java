package com.bravo.interview.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Bobby
 *
 * 中断响应
 */
public class ReentrantLockDemo3 {
    public static void main(String[] args) throws InterruptedException {
//        InterupttedResource res1 = new InterupttedResource(1);
//        InterupttedResource res2 = new InterupttedResource(2);
//
//        Thread t1 = new Thread(res1);
//        Thread t2 = new Thread(res2);
//
//        t1.start();t2.start();
//
//        TimeUnit.MILLISECONDS.sleep(5000);
//
//        t2.interrupt();

        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        InterupttedResource res1 = new InterupttedResource(lock1, lock2);
        InterupttedResource res2 = new InterupttedResource(lock2, lock1);

        Thread t1 = new Thread(res1);
        Thread t2 = new Thread(res2);

        t1.start();t2.start();

        TimeUnit.MILLISECONDS.sleep(5000);

        // 如果不中断，这里将持续死锁
        // 给t2线程状态标记为中断后，持有重入锁lock2的线程t2会响应中断，并不再继续等待lock1，同时释放了其原本持有的lock2。
        // 这样t1获取到了lock2，正常执行完成。t2也会退出，但只是释放了资源并没有完成工作。
        t2.interrupt();
    }
}

//class InterupttedResource implements Runnable{
//
//    public static ReentrantLock lock1 = new ReentrantLock();
//    public static ReentrantLock lock2 = new ReentrantLock();
//
//    public int lockInx;
//
//    public InterupttedResource (int lockInx) {
//        this.lockInx = lockInx;
//    }
//
//    @Override
//    public void run() {
//        try {
//            if(lockInx == 1){
//                lock1.lockInterruptibly();
//
//                TimeUnit.MILLISECONDS.sleep(500);
//
//                lock2.lockInterruptibly();
//            } else {
//                lock2.lockInterruptibly();
//
//                TimeUnit.MILLISECONDS.sleep(500);
//
//                lock1.lockInterruptibly();
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            if(lock1.isHeldByCurrentThread()){
//                lock1.unlock();
//            }
//            if(lock2.isHeldByCurrentThread()){
//                lock2.unlock();
//            }
//            System.out.println(Thread.currentThread().getName() + " exit.");
//        }
//
//    }
//}

class InterupttedResource implements Runnable{
    private ReentrantLock lock1;
    private ReentrantLock lock2;

    public InterupttedResource (ReentrantLock lock1, ReentrantLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        try {
            lock1.lockInterruptibly();

            TimeUnit.MILLISECONDS.sleep(500);

            lock2.lockInterruptibly();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(lock1.isHeldByCurrentThread()){
                lock1.unlock();
            }
            if(lock2.isHeldByCurrentThread()){
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + " exit.");
        }

    }
}