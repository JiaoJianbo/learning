package com.bravo.interview.jvm.ref;

import java.lang.ref.SoftReference;

/**
 * @author: Bobby
 *
 * 运行前先配置参数：
 * -Xmx10m -Xmx10m -XX:+PrintGCDetails
 *
 * 软引用需要用 java.lang.ref.SoftReference<T> 类来实现，可以让对象豁免一些垃圾收集。
 * 当系统内存充足时，它不会被回收。
 * 当系统内存不足时，它会被回收。
 *
 * 软引用通常用在对内存敏感的程序中，比如高速缓存就有用到软引用，内存够用的时候就保留，不够用就回收。
 *
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        memoryEnough();
        System.out.println("=========================");
        memoryNotEnough();
    }

    public static void memoryEnough(){
        Object obj1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(obj1);
        System.out.println("obj1 = " + obj1);
        System.out.println("reference = " + reference.get());

        obj1 = null;
        System.gc();

        System.out.println("obj1 = " + obj1);
        System.out.println("reference = " + reference.get());
    }

    public static void memoryNotEnough(){
        Object obj1 = new Object();
        SoftReference<Object> reference = new SoftReference<>(obj1);
        System.out.println("obj1 = " + obj1);
        System.out.println("reference = " + reference.get());

        obj1 = null;

        try {
            byte[] b = new byte[10*1024*1024];
        } finally {
            System.out.println("obj1 = " + obj1);
            System.out.println("reference = " + reference.get());
        }

    }
}
