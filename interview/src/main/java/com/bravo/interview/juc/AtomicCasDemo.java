package com.bravo.interview.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Bobby
 *
 * CAS (Compare and Swap), 设置新值之前，先比较是否符合预期值。它是一条CPU并发原语。
 * 它的功能是判断内存某个位置的值是否为预期值，如果是则更改为新的值，这个过程是原子的。
 *
 * CAS并发原语体现在JAVA语言中就是 sun.misc.Unsafe 类中的各个方法。调用UnSafe类中CAS方法，JVM会帮我们实现CAS汇编指令。
 *
 * java.util.concurrent.atomic 包中的类，底层就是调用 Unsafe + 自旋的方式实现 CAS 操作。
 *
 * CAS 的缺点：
 * 1. 循环时间长开销大（有自旋）。
 * 2. 只能保证一个共享变量的原子操作。-- 有多个共享变量时，还得加锁。
 * 3. 引入了 “ABA” 的问题。
 *
 */
public class AtomicCasDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.compareAndSet(0, 5);
        System.out.println("atomicInteger.get() = " + atomicInteger.get());
        atomicInteger.compareAndSet(0, 10);
        System.out.println("atomicInteger.get() = " + atomicInteger.get());
    }
}
