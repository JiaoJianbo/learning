package com.bravo.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author: Bobby
 *
 * CAS 虽然解决了原子操作的问题，但是又引入了 “ABA” 的问题。
 *
 * 解决“ABA” 问题通常办法，加时间戳或者版本号，
 * 使用 {@link java.util.concurrent.atomic.AtomicStampedReference}
 */
public class AtomicAbaDemo {
    private static AtomicInteger atomInteger = new AtomicInteger(0);

    // 特别注意：这里直接用0，而不要用 new Integer(0), 因为 AtomicStampedReference 比较两个 reference 是否相等时，用的是 ==
    private static AtomicStampedReference<Integer> stampedRef =
            new AtomicStampedReference<>(0, 1);

    public static void main(String[] args) {
        // System.out.println("new Integer(0) == 0 = " + (new Integer(0) == 0));

        // 执行一次 ABA 操作
        new Thread(() -> {
            atomInteger.compareAndSet(0, 1);
            atomInteger.compareAndSet(1, 0);
        }, "t1").start();

        // 1 -> 0
        new Thread(() -> {
            // 暂停 1 秒钟，保证 t1 完成一次 ABA 操作
            try{TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

            boolean result = atomInteger.compareAndSet(0, 100);
            System.out.println("修改成功否 = " + result + " 此时值：" + atomInteger.get());
        }, "t2").start();

        // 暂停，保证上面 t1, t2 执行完毕
        try{TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("===================== 下面解决 ABA 问题 =====================");

        // 执行一次 ABA 操作
        new Thread(() -> {
            int stamp = stampedRef.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + stamp);

            // 暂停 1 秒，保证 t4 也能取得初始版本号
            try{TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}

            stampedRef.compareAndSet(0, 1, stamp, stampedRef.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+ "\t第2次版本号：" + stampedRef.getStamp());
            stampedRef.compareAndSet(1, 0, stampedRef.getStamp(), stampedRef.getStamp() + 1);
            System.out.println(Thread.currentThread().getName()+ "\t第3次版本号：" + stampedRef.getStamp());
        }, "t3").start();

        new Thread(() -> {
            int stamp = stampedRef.getStamp();
            System.out.println(Thread.currentThread().getName() + "\t第1次版本号：" + stamp);

            // 暂停 2 秒，保证 t3 完成一次 ABA 操作
            try{TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
            boolean result = stampedRef.compareAndSet(0, 100, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName()+ "修改成功否 = " + result
                    + " 此时的值：" + stampedRef.getReference()
                    + " 此时版本号：" + stampedRef.getStamp());
        }, "t4").start();
    }
}
