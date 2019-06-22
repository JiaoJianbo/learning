package com.bravo.interview.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Bobby
 *
 * Java 中实现多线程的四种方式：
 * 1. 实现 Runnable 接口。
 * 2. 继承 Thread 类。
 * 3. 实现 Callable 接口。
 * 4. 使用线程池。
 *
 * ExecutorService 中，
 *  - execute(Runnable command) 方法来自它的父接口 Executor，没有返回值。
 *  - submit() 方法，参数可以是 Callable 的，也可以是 Runnable 的。主要是有个 Future 类型的返回值。
 *
 * Executors.newXxxxPool() 的方法，里面都是通过 new ThreadPoolExecutor 实现。
 *
 * 在实际开发当中，以下三种创建线程池的方式需要谨慎使用。因为：
 * 1. newFixedThreadPool() 和 newSingleThreadExecutor() 方法实现中，工作队列用的是 LinkedBlockingQueue。
 *    而 LinkedBlockingQueue 的最大容量是 Integer.MAX_VALUE。
 * 2. newCachedThreadPool() 方法中，最大池容量为 Integer.MAX_VALUE。
 * 因此，在极端情况下这三个方法都有可能出现 OOM 异常。
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        // 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
//        ExecutorService pool = Executors.newFixedThreadPool(3);

//        // 创建一个单线程化的线程池，它只会唯一的工作线程来执行任务，保证所有任务按照指定顺序执行。
//        ExecutorService pool = Executors.newSingleThreadExecutor();

        // 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ExecutorService pool = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " is running...");
                });
            }
        } finally {
            pool.shutdown(); // 最后不能忘记关闭池，否则程序不会终止
        }

    }
}
