package com.bravo.interview.jvm.ref;

import java.lang.ref.WeakReference;

/**
 * @author: Bobby
 *
 * 弱引用需要用 java.lang.ref.WeakReference<T> 类来实现，用来描述非必需对象的，它比软引用的生存期更短。
 * 对于软引用对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否足够，都会回收该对象占用的内存。
 * 被弱引用关联的对象只能生存到下一次垃圾收集发生之前。
 *
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference<Object> reference = new WeakReference<>(obj1);
        System.out.println("obj1 = " + obj1);
        System.out.println("reference = " + reference.get());

        obj1 = null;
        System.gc();

        System.out.println("obj1 = " + obj1);
        System.out.println("reference = " + reference.get());
    }
}
