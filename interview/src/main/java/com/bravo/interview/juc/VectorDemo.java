package com.bravo.interview.juc;

import java.util.Vector;

/**
 * @author Bobby
 *
 * Vector 虽说是线程安全的，但也不是绝对的线程安全。
 *
 * 线程安全：
 * 当多个线程访问一个对象时，如果不考虑这些线程在运行时环境下的调度和交替执行，也不需要进行额外的同步，
 * 或者在调用方进行任何其他的协调操作，调用这个对象的行为都可以获得正确的结果，那这个对象就是线程安全的。
 *
 * 相对线程安全就是我们通常意义上所讲的线程安全，它需要保证对这个对象单独的操作是线程安全的，我们在调用的时候不需要做额外的保障措施，
 * 但是对于一些特定顺序的连续调用，就可能需要在调用端使用额外的同步手段来保证调用的正确性。
 * 在 Java 语言中，大部分的线程安全类都属于这种，如 Vector，HashTable，Collections 的 synchronizedCollection() 方法包装的集合类等。
 *
 */
public class VectorDemo {
    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true){
            for(int i=0; i<10; i++){
                vector.add(i);
            }

            new Thread(() -> {
                for(int i=0; i<vector.size(); i++) {
                    vector.remove(i);
                }
            }).start();

            new Thread(() -> {
//                for(int i=0; i<vector.size(); i++) {
//                    System.out.println(vector.get(i));
//                }
                vector.forEach(System.out::println);
            }).start();

            // 不要同时产生过多的线程，否则会导致操作系统假死
            while(Thread.activeCount() > 20);
        }

    }

}
