package com.bravo.interview.juc;

import java.util.concurrent.TimeUnit;

/**
 * 模拟 Deadlock
 *
 * @author: Bobby
 * @since 2019/5/17 18:41
 *
 *
 * 出现死锁后解决分析方法，参考 https://www.cnblogs.com/flyingeagle/articles/6853167.html
 *
 * 第一种方法：使用jConsole 分析. 线程Tab --> 检查死锁
 * 第二种方法：jps -l, 查看Java进程列表。 jstack [进程ID] 或用 jstack 输出dump文件
 */
public class DeadlockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new DeadResource(lockA, lockB), "A").start();
        new Thread(new DeadResource(lockB, lockA), "B").start();
    }
}

class DeadResource implements Runnable {
    private Object lockA = new Object();
    private Object lockB = new Object();

    public DeadResource(Object lockA, Object lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + " hold the lock.");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + " hold lock.");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
