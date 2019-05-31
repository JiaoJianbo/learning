package com.bravo.interview.juc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;

/**
 * @author: Bobby
 *
 * List 线程安全的实现，除过 Vector（Since JDK 1.0, 现在不推荐使用）外，还有：
 *   - Collections.synchronizedList(List<T> list)     -- Since JDK 1.2, 内部实现一个 SynchronizedList，方法中使用 synchronized 加锁。
 *   - java.util.concurrent.CopyOnWriteArrayList<E>   -- Since JDK 1.5, 方法内部使用 ReentrantLock 加锁，该类有个 private transient volatile Object[] array 属性，用来存放元素。
 *
 * 同理，Set 线程安全的实现方式有：
 *   - Collections.synchronizedSet(Set<T> s)
 *   - java.util.concurrent.CopyOnWriteArraySet<E>    -- 内部基于 CopyOnWriteArrayList 实现。
 *       <code>
 *          public CopyOnWriteArraySet() {
 *              this.al = new CopyOnWriteArrayList();
 *          }
 *       </code>
 *
 * Map 线程安全的实现，除过 HashTable（Since JDK 1.0, 现在不推荐使用）外，还有：
 *   - Collections.synchronizedMap(Map<K,V> m)
 *   - java.util.concurrent.ConcurrentHashMap<K,V>     -- 是一个 Segment 数组，Segment 通过继承 ReentrantLock 来进行加锁，所以每次需要加锁的操作锁住的是一个 segment（分段锁）。
 *
 * 引申：
 * Java 8 中 HashMap 和 ConcurrentHashMap 较 Java 7 中，又引入了红黑树。当链表中的元素达到了 8 个时，会将链表转换为红黑树。
 * 参考：<a href="http://www.importnew.com/28263.html">Java7/8 中的 HashMap 和 ConcurrentHashMap 全解析</a>
 */
public class SynchronizedCollectionDemo {

    public static void main(String[] args) {
//        nonSafeList();

//        synchronizedList();

        copyOnWriteArrayList();
    }

    // 偶尔会抛异常 Exception in thread "Thread-xx" java.util.ConcurrentModificationException
    public static void nonSafeList(){
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName());
                System.out.println("list = " + list + "\t size = " + list.size());
            }).start();
        }

        while (Thread.activeCount() > 2) { //直到所有线程运行完
            Thread.yield();
        }

        System.out.println("list = " + list + "\t size = " + list.size());
    }

    // 线程安全
    public static void synchronizedList(){
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                synchronizedList.add(Thread.currentThread().getName());
                System.out.println("list = " + synchronizedList + "\t size = " + synchronizedList.size());
            }).start();
        }
    }


    // 线程安全
    public static void copyOnWriteArrayList(){
        List<String> cwList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                cwList.add(Thread.currentThread().getName());
                System.out.println("list = " + cwList + "\t size = " + cwList.size());
            }).start();
        }
    }
}
