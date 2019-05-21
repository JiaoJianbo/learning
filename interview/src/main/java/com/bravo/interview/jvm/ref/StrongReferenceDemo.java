package com.bravo.interview.jvm.ref;

import java.util.concurrent.TimeUnit;

/**
 * @author: Bobby
 *
 * 强引用是最常见的普通对象引用（默认支持的就是强引用），
 * 只要还有前引用指向一个对象，就能表明对象还“活着”，垃圾收集器不会碰到这种对象。
 *
 * 对于强引用的对象，就算是出现了OOM也不会对该对象进行回收。即使该对象以后永远都不能被用到。
 * 对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显示地将相应（强）引用赋值为null，
 * 一般认为就是可以被垃圾收集的了（当然具体回收时还要看垃圾收集策略）。
 */
public class StrongReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = obj1;

        obj1 = null;
        System.gc();
        try { TimeUnit.SECONDS.sleep(2L); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("obj1 = " + obj1);
        System.out.println("obj2 = " + obj2);
    }

}
