package com.bravo.interview.juc;

import java.util.concurrent.TimeUnit;

/**
 * @author Bobby
 *
 * 题目：
 * 建立三个线程，A 线程打印10次 A，B 线程打印10次 B, C 线程打印10次 C，要求线程同时运行，交替打印10次 ABC
 * 即打印出：ABCABCABCABCABCABCABCABCABCABC
 */
public class MyThreadPrinter implements Runnable {
    private String name;
    private Object pre;
    private Object self;

    public MyThreadPrinter(String name, Object pre, Object self) {
        this.name = name;
        this.pre = pre;
        this.self = self;
    }

    @Override
    public void run() {
        int count = 10;

        while (count > 0){
            synchronized (pre){
                synchronized (self){
                    System.out.print(this.name);
                    count--;
                    self.notify();
                }

                try {
                    pre.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Thread(new MyThreadPrinter("A", c, a)).start();
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(new MyThreadPrinter("B", a, b)).start();
        TimeUnit.MILLISECONDS.sleep(200);
        new Thread(new MyThreadPrinter("C", b, c)).start();
        TimeUnit.MILLISECONDS.sleep(200);

        System.exit(0); //结束当前程序
    }
}
