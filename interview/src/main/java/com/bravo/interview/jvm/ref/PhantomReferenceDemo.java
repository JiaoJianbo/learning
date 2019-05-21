package com.bravo.interview.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author: Bobby
 *
 * 虚引用需要java.lang.ref.PhantomReference类来实现。
 * 如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收。
 * 它不能单独使用也不能通过它访问对象，虚引用必须和队列（ReferenceQueue）联合使用。
 *
 * PhantomReference 的get方法总是返回 null，因此无法访问对应的引用对象。
 * 设置虚引用关联的唯一目的，就是在这个对象被收集器回收的时候收到一个系统通知或者后续添加进一步的处理。
 */
public class PhantomReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference reference = new PhantomReference(obj1, refQueue);

        System.out.println("obj1 = " + obj1);
        System.out.println("reference = " + reference.get()); // This method always returns null.
        System.out.println("queue.poll = " + refQueue.poll());

        obj1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println("===========================");

        System.out.println("obj1 = " + obj1);
        System.out.println("reference = " + reference.get());
        System.out.println("queue.poll = " + refQueue.poll());
    }
}
